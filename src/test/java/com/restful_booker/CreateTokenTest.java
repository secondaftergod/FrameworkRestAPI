package com.restful_booker;

import com.restful_booker.constants.Constants;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

public class CreateTokenTest extends  BookerBase{
    @Test
    public void postCrateToken(){
        JSONObject passJSON = new JSONObject();
        passJSON.put("username",  Constants.USERNAME_ADMIN);
        passJSON.put( "password", Constants.PASSWORD);

        Response tokenResponse = createNewToken(passJSON);

        tokenResponse.prettyPrint();

        String jsonString = tokenResponse.getBody().asString();
        String result= JsonPath.from(jsonString).getString("token");
        System.out.println("token_response: "+result);

    }


}