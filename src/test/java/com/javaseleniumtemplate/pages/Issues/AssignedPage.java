package com.javaseleniumtemplate.pages.Issues;

import com.javaseleniumtemplate.bases.PageBase;
import org.openqa.selenium.By;

public class AssignedPage extends PageBase {
    String assigned = "//a[text()=###]";

    public Boolean verificarAssigned(String idIssue) {
        assigned = assigned.replace("###", idIssue);
        return returnIfElementIsDisplayed(By.xpath(assigned));
    }

}
