package ru.netology.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import ru.netology.data.DataBaseHelper;
import ru.netology.pages.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Test1 {
    private SelenideElement form;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        form = $("[id = root]").shouldBe(Condition.visible);

    }

    @AfterAll
     static void cleanTables() {
        DataBaseHelper.cleanDatabase();
    }

    @Order(1)
    @Test
    void shouldUpdate(){
        cleanTables();
        DataBaseHelper.updateUsersTable();
    }

    @Order(3)
    @Test
    void shouldOpenDashBord() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.setValidLoginData();
        String code = DataBaseHelper.findCode();
        verificationPage.validVerify(code);
    }

    @Order(2)
    @Test
    void shouldNotLogin() {
        val loginPage = new LoginPage();
        loginPage.setIncorrectLoginData();
        loginPage.showErrorMessage();
    }

    @Order(4)
    @Test
    void shouldShowExceptionWhenPasscodeAreIncorrect() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.setValidLoginData();
        verificationPage.setIncorrectPasscode();
        verificationPage.showErrorMessage();
    }
}
