package ru.netology.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private SelenideElement codeField = $("[data-test-id = code] input");
    private SelenideElement button = $("[data-test-id = action-verify]");

    public VerificationPage() {
        codeField.shouldBe(Condition.visible);
    }

    public static void showErrorMessage() {
        $(withText("Неверно указан код")).shouldBe(Condition.visible);
    }

    public DashboardPage validVerify(String code) {
        codeField.setValue(code);
        button.click();
        return new DashboardPage();
    }

    public void setIncorrectPasscode() {
        for (int i = 0; i <=3; i++) {
            codeField.setValue(String.valueOf(i));
            button.click();
            showErrorMessage();
        }
        button.click();
    }
}
