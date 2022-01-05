package com.javaseleniumtemplate.utils;

import com.javaseleniumtemplate.GlobalParameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Browsers {

    private static String downloadPath = GlobalParameters.DOWNLOAD_DEFAULT_PATH;
    private static String seleniumHub = GlobalParameters.SELENIUM_HUB;

    public static WebDriver getLocalChrome(){
        Map<String,Object> prefs = new HashMap<String,Object>();
        prefs.put("download.prompt_for_download",false);
        prefs.put("download.extensions_to_open","application/xml");
        prefs.put("safebrowsing.enabled",true);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("no-sandbox");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("--lang=eng-US");
        chromeOptions.addArguments("--safebrowsing-disable-download-protection");
        chromeOptions.addArguments("download.default_directory", downloadPath);
        chromeOptions.addArguments("--disable-extensions");
        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver getRemoteChrome(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("no-sandbox");
        chromeOptions.addArguments("--allow-running-insecure-content");
        chromeOptions.addArguments("download.default_directory", downloadPath);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        URL url = null;
        try {
            url = new URL(seleniumHub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new RemoteWebDriver(url, capabilities);
    }

    public static WebDriver getLocalChromeHeadless(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("download.default_directory", downloadPath);
        chromeOptions.addArguments("--headless");

        return new ChromeDriver(chromeOptions);
    }

    public static WebDriver getRemoteChromeHeadless(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("download.default_directory", downloadPath);
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--lang=pt-BR");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("window-size=1920,1080");
        chromeOptions.addArguments("--allow-running-insecure-content");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        URL url = null;
        try {
            url = new URL(seleniumHub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new RemoteWebDriver(url, capabilities);
    }

    public static WebDriver getLocalFirefox(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages", "eng-US");
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.download.viewableInternally.enabledTypes", "");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/vnd.ms-excel,application/msword");
        profile.setPreference("browser.helperApps.alwaysAsk.force", false);
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setProfile(profile);
        return new FirefoxDriver(firefoxOptions);
    }

    public static WebDriver getRemoteFirefox(){
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.useDownloadDir", true);
        profile.setPreference("browser.download.viewableInternally.enabledTypes", "");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "text/csv,application/vnd.ms-excel,application/msword");

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability("browser.download.dir", downloadPath);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        URL url = null;
        try {
            url = new URL(seleniumHub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new RemoteWebDriver(url, capabilities);
    }

    public static WebDriver getLocalInternetExplorer(){
        return new InternetExplorerDriver();
    }

    public static WebDriver getLocalOpera(){
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.addArguments("--lang=eng-US");
        return new OperaDriver(operaOptions);
    }

    public static WebDriver getRemoteOpera(){
        DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();

        URL url = null;
        try {
            url = new URL(seleniumHub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new RemoteWebDriver(url, capabilities);
    }
    public static WebDriver getRemoteInternetExplorer(){
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

        URL url = null;
        try {
            url = new URL(seleniumHub);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new RemoteWebDriver(url, capabilities);
    }
}
