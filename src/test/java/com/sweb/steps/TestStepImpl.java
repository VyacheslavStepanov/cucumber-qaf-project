package com.sweb.steps;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.sweb.pages.TempMailPage;

import static com.qmetry.qaf.automation.step.CommonStep.*;
import static java.lang.String.format;

/**
 * @Author sweb
 */

public class TestStepImpl {
    static private String temp_mail;

    @QAFTestStep(description = "I click to {locator}")
    public static void clickLink(String locator) {
        click(locator);
    }

    @QAFTestStep(description = "I fill tempmail as {inputName}")
    public static void fillTempMail(String inputName) {
        temp_mail = TempMailPage.getTempMail();
        String locator= format("xpath=//input[@name='%s' or @id='%s']", inputName, inputName);
        sendKeys(temp_mail,locator);
    }

    @QAFTestStep(description = "I confirm registration at temp mail")
    public static void confirmRegistation() {
        //TempMailPage.confirmRegistation();
    }

    @QAFTestStep(description = "I fill {value} as {inputName}")
    public static void fillInput(String value, String inputName) {

        String locator= format("xpath=//input[@name='%s' or @id='%s']", inputName, inputName);
        waitForPresent(locator, 1);
        sendKeys(value, locator);
    }

    @QAFTestStep(description = "I should see Регистрация завершена")
    public static void verifyRegistrationTitle() {
        String locator = "xpath=//article/div[contains(.,'Регистрация завершена.')]";
        waitForPresent(locator, 3);
        assertPresent(locator);
    }

    @QAFTestStep(description = "I should see Логин или пароль неверный")
    public static void verifyWrongLogin() {
        String locator = "xpath=//div[@class='message' and contains(.,'Логин или пароль неверный. Восстановить пароль')]";
        waitForPresent(locator, 3);
        assertPresent(locator);
    }

    @QAFTestStep(description = "I should see Мой профиль")
    public static void verifylogin() {
        String locator = "xpath=//a[contains(.,'Мой профиль ')]";
        waitForPresent(locator, 3);
        assertPresent(locator);
    }

    @QAFTestStep(description = "login with name {username} and password {password}")
    public static void loginWithDataProvider(String username, String password) {
        click("xpath=//a[@href='#/login']");
        sendKeys(username, "xpath=//input[@name='login']");
        sendKeys(password, "xpath=//input[@id='passkey-pwd']");
        click("xpath=//button[contains(.,'Войти')]");
    }

    @QAFTestStep(description = "logout from site")
    public static void logout2() {
        click("xpath=//a[contains(.,'Мой профиль ')]");
        click("xpath=//a[contains(.,'Выйти')]");
    }

    @QAFTestStep(description = "verify result is {expectedResult}")
    public static void loginResult(Boolean expectedResult) {
        if (expectedResult) {
            assertPresent("xpath=//a[contains(.,'Мой профиль ')]");
            logout2();
        } else {
            assertNotPresent("xpath=//a[contains(.,'Мой профиль ')]");
        }

    }


}
