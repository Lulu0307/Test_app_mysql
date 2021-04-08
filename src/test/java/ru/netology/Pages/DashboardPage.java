package ru.netology.Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private SelenideElement dashboard = $("[data_test_id = dashboard]");

    public DashboardPage(){
       $(withText("Личный кабинет")).shouldBe(Condition.visible);
    }
}
