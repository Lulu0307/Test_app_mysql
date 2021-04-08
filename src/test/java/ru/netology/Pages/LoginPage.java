package ru.netology.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.Data.UserData;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private SelenideElement loginField = $("[data-test-id = login] input");
    private SelenideElement passwordField = $("[data-test-id = password] input");
    private SelenideElement loginButton = $("[data-test-id = action-login]");

    public LoginPage() { loginField.shouldBe(Condition.visible); }

    public VerificationPage setValidLoginData() {
        loginField.setValue(UserData.validAuthInfo().getLogin());
        passwordField.setValue(UserData.validAuthInfo().getPassword());
        loginButton.click();
        return new VerificationPage();
    }

    public void setIncorrectLoginData() {
        loginField.setValue(UserData.incorrectAuthInfo().getLogin());
        passwordField.setValue(UserData.incorrectAuthInfo().getPassword());
        loginButton.click();
    }
}
