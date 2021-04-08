package ru.netology.Data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

public class UserData {

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo validAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo incorrectAuthInfo() {
        val faker = new Faker();
        val name = faker.name().username();
        val password = faker.internet().password();
        return new AuthInfo(name, password);
    }


}

