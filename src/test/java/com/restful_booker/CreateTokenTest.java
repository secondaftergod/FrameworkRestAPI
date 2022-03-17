package com.restful_booker;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreateTokenTest extends  BookerBase{
    @Test
    public void postCrateToken(){
        JSONObject passJSON = new JSONObject();
        passJSON.put("username",  "admin");
        passJSON.put( "password", "password123");

        Response tokenResponse = createNewToken(passJSON);

        tokenResponse.prettyPrint();

        String jsonString = tokenResponse.getBody().asString();
        String result= JsonPath.from(jsonString).getString("token");
        System.out.println("token_response: "+result);

    }


}