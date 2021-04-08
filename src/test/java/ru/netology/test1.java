package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import ru.netology.Pages.LoginPage;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class test1 {
    private SelenideElement form;

    @BeforeEach
    @Test
    void setUp() {
        open("http://localhost:9999");
        form = $("[id = root]").shouldBe(Condition.visible);

    }

    @AfterAll
     static void cleanTables() {
        try {
            DataBaseHelper.cleanDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        String code = null;
        try {
            code = DataBaseHelper.findCode();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        verificationPage.validVerify(code);
    }

    @Order(2)
    @Test
    void shouldNotLogin() {
        val loginPage = new LoginPage();
        loginPage.setIncorrectLoginData();
        $(withText("Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Order(4)
    @Test
    void shouldShowExceptionWhenPasscodeAreIncorrect() {
        val loginPage = new LoginPage();
        val verificationPage = loginPage.setValidLoginData();
        verificationPage.setIncorrectPasscode();
        $(withText("Превышено количество попыток входа")).shouldBe(Condition.visible);
    }
}
