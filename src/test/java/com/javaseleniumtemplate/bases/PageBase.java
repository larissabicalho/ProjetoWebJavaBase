package com.javaseleniumtemplate.bases;

import com.javaseleniumtemplate.GlobalParameters;
import com.javaseleniumtemplate.defaultParameters.GlobalStaticParameters;
import com.javaseleniumtemplate.utils.DriverFactory;
import com.javaseleniumtemplate.utils.ExtentReportUtils;
import com.javaseleniumtemplate.utils.WebdavUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.lang3.time.StopWatch;

import java.io.File;
import java.io.IOException;

public class PageBase {
    //Variaveis globlais
    protected WebDriverWait wait = null;
    protected WebDriver driver = null;
    protected JavascriptExecutor javaScriptExecutor = null;
    protected static final String style = "style";

    //Construtor
    public PageBase(){
        wait = new WebDriverWait (DriverFactory.INSTANCE, GlobalParameters.TIMEOUT_DEFAULT);
        driver = DriverFactory.INSTANCE;
        javaScriptExecutor = (JavascriptExecutor) driver;
    }

    //Custom Actions
    private void waitUntilPageReady(){
        StopWatch timeOut = new StopWatch();
        timeOut.start();

        while (timeOut.getTime() <= GlobalParameters.TIMEOUT_DEFAULT)
        {
            String documentState = javaScriptExecutor.executeScript("return document.readyState").toString();
            if (documentState.equals("complete")){
                timeOut.stop();
                break;
            }
        }
    }

    protected WebElement waitForElement(By locator){
        waitUntilPageReady();
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }


    protected WebElement waitForElementByTime(By locator, int time){
        waitUntilPageReady();
        WebDriverWait waitTime = new WebDriverWait(driver, time);
        waitTime.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        waitTime.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    protected WebElement waitForElementDisabled(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        return element;
    }

    //Função usada para acessar os elementos que estão dentro de um #shadow-root
    //Ex:  WebElement root = driver.findElement(By.tagName("driver-app-shell"))---> elemento onde se encontra o shadow-root
    //     WebElement shadowRoot = expandShadowRootElement(root); ----> pegando os elementos que estão dentro do shadow-root
    protected WebElement expandShadowRootElement(By locator) {
        WebElement shadowRootElement = (WebElement) javaScriptExecutor.executeScript("return arguments[0].shadowRoot", waitForElement(locator));
        return shadowRootElement;
    }

    protected void click(By locator){
        WebDriverException possibleWebDriverException = null;
        StopWatch timeOut = new StopWatch();
        timeOut.start();

        while (timeOut.getTime() <= GlobalParameters.TIMEOUT_DEFAULT)
        {
            WebElement element = null;

            try
            {
                element = waitForElement(locator);
                element.click();
                timeOut.stop();
                ExtentReportUtils.addTestInfo(3, "");
                return;
            }

            catch (StaleElementReferenceException e)
            {
                continue;
            }

            catch (WebDriverException e)
            {
                possibleWebDriverException = e;
                if (e.getMessage().contains("Other element would receive the click"))
                {
                    continue;
                }

                throw e;
            }
        }

        try {
            throw new Exception(possibleWebDriverException);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void sendKeys(By locator, String text){
        waitForElement(locator).sendKeys(text);
        ExtentReportUtils.addTestInfo(3, "PARAMETER: " + text);
    }

    protected void sendKeysWithoutWaitVisible(By locator, String text){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        element.sendKeys(text);
        ExtentReportUtils.addTestInfo(3, "PARAMETER: " + text);
    }

    protected void clear(By locator){
        WebElement webElement = waitForElement(locator);
        webElement.clear();
    }

    protected void clearAndSendKeys(By locator, String text){
        WebElement webElement = waitForElement(locator);
        webElement.sendKeys(Keys.CONTROL + "a");
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(text);
    }

    protected void comboBoxSelectByVisibleText(By locator, String text){
        Select comboBox = new Select(waitForElement(locator));
        comboBox.selectByVisibleText(text);
        ExtentReportUtils.addTestInfo(3, "PARAMETER: " + text);
    }

    protected void comboBoxSelectByOption(By locator, String index){
        Select comboBox = new Select(waitForElement(locator));
        comboBox.selectByValue(index);
        ExtentReportUtils.addTestInfo(3, "PARAMETER: " + index);
    }

    protected void mouseOver(By locator){
        Actions action = new Actions(driver);
        action.moveToElement(waitForElement(locator)).build().perform();
        ExtentReportUtils.addTestInfo(3, "");
    }

    protected String getText(By locator){
        String text = waitForElement(locator).getText();
        ExtentReportUtils.addTestInfo(3, "RETURN: " + text);
        return text;
    }

    protected String getValue(By locator){
        String text = waitForElement(locator).getAttribute("value");
        ExtentReportUtils.addTestInfo(3, "RETURN: " + text);
        return text;
    }

    protected boolean returnIfElementIsDisplayed(By locator){
        boolean result = waitForElement(locator).isDisplayed();
        ExtentReportUtils.addTestInfo(3, "RETURN: " + result);
        return result;
    }

    protected boolean returnIfElementExists(By locator){
        boolean result = false;

        try
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            result = true;
        }
        catch (Exception e)
        {

        }
        ExtentReportUtils.addTestInfo(3, "RETURN: " + result);
        return result;
    }


    protected boolean returnIfElementIsEnabled(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        boolean result = driver.findElement(locator).isEnabled();
        ExtentReportUtils.addTestInfo(3, "RETURN: " + result);
        return result;
    }

    protected boolean returnIfElementIsSelected(By locator){
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        boolean result = driver.findElement(locator).isSelected();
        ExtentReportUtils.addTestInfo(3, "RETURN: " + result);
        return result;
    }

    //Javascrip actions
    protected void SendKeysJavaScript(By locator, String value){
        WebElement element = waitForElement(locator);
        javaScriptExecutor.executeScript("arguments[0].value='" + value + "';", element);
        ExtentReportUtils.addTestInfo(3, "PARAMETER: " + value);
    }

    protected void ClickJavaScript(By locator){
        WebElement element = waitForElement(locator);
        javaScriptExecutor.executeScript("arguments[0].click();", element);
        ExtentReportUtils.addTestInfo(3, "");
    }

    protected void ScrollToElementJavaScript(By locator){
        WebElement element = waitForElement(locator);
        javaScriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
        ExtentReportUtils.addTestInfo(3, "");
    }

    protected void ScrollToTop(){
        javaScriptExecutor.executeScript("window.scrollTo(0, 0);");
        ExtentReportUtils.addTestInfo(3, "");
    }

    //Default actions
    public void refresh(){
        DriverFactory.INSTANCE.navigate().refresh();
        ExtentReportUtils.addTestInfo(2, "");
    }

    public void navigateTo(String url){
        DriverFactory.INSTANCE.navigate().to(url);
        ExtentReportUtils.addTestInfo(2, "PARAMETER: " + url);
    }

    public void openNewTab(){
        javaScriptExecutor.executeScript("window.open();");
        ExtentReportUtils.addTestInfo(2, "");
    }
    public void closeTab(){
        driver.close();
        ExtentReportUtils.addTestInfo(2, "");
    }

    public String getTitle(){
        String title = driver.getTitle();
        ExtentReportUtils.addTestInfo(2, "");
        return title;
    }

    public String getURL(){
        String url = driver.getCurrentUrl();
        ExtentReportUtils.addTestInfo(2, "");
        return url;
    }

    public boolean verificarSeContemArquivoNaPastaExcel() throws IOException {

        if(GlobalParameters.EXECUTION.contains("remota")) {

            String webdavResource = GlobalParameters.WEB_DAV;

            int time = 0;
            while (time <= 60) {
                if (WebdavUtils.getResourcesFrom(webdavResource).isEmpty()) {
                    new Actions(driver).pause(1000).perform();
                    time++;
                } else {
                    WebdavUtils.deleteResourceExcel(webdavResource);
                    return true;

                }
            }
        }else if (GlobalParameters.EXECUTION.contains("local")) {
            File downloads = new File(GlobalStaticParameters.xml);

            int time = 0;
            while (time <= 60) {

                if (downloads.length() != 0) {
                    downloads.delete();
                    return true;
                }
                else {
                    new Actions(driver).pause(1000).perform();
                    time++;
                }
            }
        }
        return false;

    }
    public boolean verificarSeContemArquivoNaPastaDoc() throws IOException {

        if(GlobalParameters.EXECUTION.contains("remota")) {

            String webdavResource = GlobalParameters.WEB_DAV;

            int time = 0;
            while (time <= 60) {
                if (WebdavUtils.getResourcesFrom(webdavResource).isEmpty()) {
                    new Actions(driver).pause(1000).perform();
                    time++;
                } else {
                    WebdavUtils.deleteResourceDOC(webdavResource);
                    return true;

                }
            }
        }else if (GlobalParameters.EXECUTION.contains("local")) {
            File downloads = new File(GlobalStaticParameters.doc);

       //     File[] files = downloads.listFiles();
            int time = 0;
            while (time <= 60) {

                if (downloads.length() != 0) {
                    downloads.delete();
                    return true;
                }
                else {
                    new Actions(driver).pause(1000).perform();
                    time++;
                }
            }
        }
        return false;

    }

    public boolean verificarSeContemArquivoNaPastaCSV() throws IOException {

        if(GlobalParameters.EXECUTION.contains("remota")) {

            String webdavResource = GlobalParameters.WEB_DAV;

            int time = 0;
            while (time <= 60) {
                if (WebdavUtils.getResourcesFrom(webdavResource).isEmpty()) {
                    new Actions(driver).pause(1000).perform();
                    time++;
                } else {
                    WebdavUtils.deleteResourceCSV(webdavResource);
                    return true;

                }
            }
        }else if (GlobalParameters.EXECUTION.contains("local")) {
            File downloads = new File(GlobalStaticParameters.csv);
            int time = 0;
            while (time <= 60) {

                if (downloads.length() != 0) {
                    downloads.delete();
                    return true;
                }
                else {
                    new Actions(driver).pause(1000).perform();
                    time++;
                }
            }
        }
        return false;


    }

    public static void defineAtributoElemento(WebDriver driver, WebElement elemento, String atributo, String valor) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", new Object[]{elemento, atributo, valor});
    }

    public void dropBox(By upload) {
        WebElement inputUpload = driver.findElement(upload);
        String valorStyle = inputUpload.getAttribute(style);
        defineAtributoElemento(driver, inputUpload, style, "");
    }

    public void dropDrown(String projectName, String drop){
        Actions action = new Actions(driver);
        WebElement list = driver.findElement(By.id(drop));
        list.click();
        action.moveToElement(list);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Now I need to scroll down till find my desire project in the list.

        WebElement Project = driver.findElement(By.xpath("//*[text()= ' " + projectName + " ']"));
        js.executeScript("arguments[0].click();", Project);
    }
}
