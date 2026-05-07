package com.bojidar.avenga.bookstore.utils;

public class Config {

    public static String getBaseUrl() {
        return System.getProperty("baseUrl",
                "https://fakerestapi.azurewebsites.net");
    }
}