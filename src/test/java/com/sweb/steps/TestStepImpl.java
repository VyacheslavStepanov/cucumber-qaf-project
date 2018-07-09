package com.sweb.steps;

import com.qmetry.qaf.automation.step.QAFTestStep;
import static com.qmetry.qaf.automation.step.CommonStep.*;
import static com.qmetry.qaf.automation.ui.webdriver.ElementFactory.$;

/**
 * @Author sweb
 */

public class TestStepImpl {
    static private String temp_mail;

    @QAFTestStep(description = "I am on main Page")
    public void openPage() throws Throwable {
        get("http://business-gazeta.ru");
        assertPresent("xpath=//strong[contains(.,'БИЗНЕС Online')]");
    }

    @QAFTestStep(description = "I register account")
    public static void registerAccount() {
        // получаем временную почту
        get("https://temp-mail.org");
        temp_mail = $("xpath=//input[@id='mail']").getAttribute("value");

        get("http://business-gazeta.ru");
        click("xpath=//a[@href='#/login']");

        click("xpath=//a[@href='/registration']");
        sendKeys("user_name", "xpath=//input[@name='name']");
        sendKeys(temp_mail, "xpath=//input[@name='nickname']");
        sendKeys(temp_mail, "xpath=//input[@name='email']");
        sendKeys("1001.2000", "xpath=//input[@name='dob']");
        sendKeys("123456", "xpath=//input[@id='password-passkey']");
        sendKeys("123456", "xpath=//input[@id='password-passkey_repeat']");
        click("xpath=//input[@id='radio-sex-1']");
        click("xpath=//input[@name='pdn_agree']");
        click("xpath=//input[@name='agree']");
        click("xpath=//input[@type='submit']");
        assertPresent("xpath=//article/div[contains(.,'Регистрация завершена.')]");

        // подтверждаем регистрацию
        get("https://temp-mail.org");
        click("xpath=//a[contains(.,'Подтверждение регистрации')]");
        click("xpath=//a[contains(.,'подтвердить')]");
    }

    @QAFTestStep(description = "I can login to system")
    public static void login() {
        get("http://business-gazeta.ru");
        click("xpath=//a[@href='#/login']");
        sendKeys(temp_mail, "xpath=//input[@name='login']");
        sendKeys("123456", "xpath=//input[@id='passkey-pwd']");
        click("xpath=//button[contains(.,'Войти')]");
        assertPresent("xpath=//a[contains(.,'Мой профиль ')]");
    }

    @QAFTestStep(description = "I can edit profile")
    public static void editProfile() {
        click("xpath=//a[contains(.,'Мой профиль ')]");
        click("xpath=//a[contains(.,'Редактировать профиль')]");
        assertPresent("xpath=//span[contains(.,'Пользователь:')]");
        click("xpath=//li/a[contains(.,'Редактировать профиль')]");

        sendKeys("MyCompany", "xpath=//input[@name='work']");
        click("xpath=//input[@name='save']");
        click("xpath=//a[contains(.,'Мой профиль ')]");
        click("xpath=//a[contains(.,'Редактировать профиль')]");
        assertPresent("xpath=//dd[contains(.,'MyCompany')]");
    }

    @QAFTestStep(description = "I can edit password")
    public static void editpassword() {
        click("xpath=//a[contains(.,'Мой профиль ')]");
        click("xpath=//a[contains(.,'Редактировать профиль')]");
        assertPresent("xpath=//span[contains(.,'Пользователь:')]");
        click("xpath=//a[contains(.,'Сменить пароль')]");
        String password = "654321";
        sendKeys("MyCompany", "xpath=//input[@name='passkey']");
        sendKeys("MyCompany", "xpath=//input[@name='passkey_repeat']");
        click("xpath=//input[@value='Изменить']");
        assertPresent("xpath=//h1[contains(.,'Пароль успешно изменен')]");
    }
    @QAFTestStep(description = "I can logout")
    public static void logout1() {
        click("xpath=//a[contains(.,'Мой профиль ')]");
        click("xpath=//a[contains(.,'Выйти')]");
        assertNotPresent("xpath=//a[contains(.,'Мой профиль ')]");
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
