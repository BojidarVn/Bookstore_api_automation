package com.bojidar.avenga.bookstore.base;

import com.bojidar.avenga.bookstore.utils.Config;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = Config.getBaseUrl();
        RestAssured.replaceFiltersWith(new AllureRestAssured());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}