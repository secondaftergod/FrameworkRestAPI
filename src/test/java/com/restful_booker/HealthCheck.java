package com.restful_booker;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HealthCheck
{
    @Test
    public void healthCheckTest()
    {
        given().when().get("https://restful-booker.herokuapp.com/ping").
                    then().
                    assertThat().statusCode(201);
    }
}
