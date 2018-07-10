package com.sweb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public final class TempMailPage {
    static  ChromeDriver driver2 = new ChromeDriver();

    public static String getTempMail(){
        driver2.get("https://temp-mail.org");
        WebElement element = driver2.findElementByXPath("//input[@id='mail']");
        String mail = element.getAttribute("value");
        return mail;
    }
    public static void confirmRegistation(){
        driver2.findElementByXPath("//a[contains(.,'Подтверждение регистрации')]").click();
        driver2.findElementByXPath("//a[contains(.,'подтвердить')]").click();
        driver2.quit();
    }
}
