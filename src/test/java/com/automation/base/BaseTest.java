package com.automation.base;

import com.automation.config.ConfigReader;
import com.automation.service.BookingService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {


    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
    }
}
