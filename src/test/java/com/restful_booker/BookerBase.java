package com.restful_booker;

import com.restful_booker.constants.Constants;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import static com.restful_booker.constants.Constants.URL;
import static com.restful_booker.constants.Constants.URL_BOOKS;

public class BookerBase {

    protected RequestSpecification startURL;
    protected RequestSpecification bookURL;

    @BeforeMethod
    public void beforeATest()
    {
        startURL = new RequestSpecBuilder().
                setBaseUri(URL).build();

        bookURL = new RequestSpecBuilder().
                setBaseUri(URL_BOOKS).build();
    }
    public Response createNewToken(JSONObject inputUser){
        Response response;
        response = RestAssured.given().
                spec(startURL).
                contentType(ContentType.JSON).
                body(inputUser.toString()).
                post("/auth");
        return response;
    }
    public Response createNewBooking(JSONObject inputUser) {
        Response response =
                RestAssured.given().
                        spec(bookURL).
                        contentType(ContentType.JSON).
                        body(inputUser.toString()).
                        post()                 ;
        return response;
    }
    public Response updateBookPut(JSONObject inputUser, int ID) {
        Response response =
                RestAssured.given().
                        spec(bookURL).
                        auth().
                        preemptive().
                        basic(Constants.USERNAME_ADMIN, Constants.PASSWORD).
                        contentType(ContentType.JSON).
                        body(inputUser.toString()).
                        put(String.valueOf(ID))                 ;
        return response;
    }

    public Response updateBookPatch(JSONObject inputUser, int ID) {
        Response response =
                RestAssured.given().
                        spec(bookURL).
                        auth().
                        preemptive().
                        basic(Constants.USERNAME_ADMIN, Constants.PASSWORD).
                        contentType(ContentType.JSON).
                        body(inputUser.toString()).
                        patch(String.valueOf(ID))              ;
        return response;
    }
    public Response deleteBook(int ID) {
        Response response =
                RestAssured.given().
                        spec(bookURL).
                        auth().
                        preemptive().
                        basic(Constants.USERNAME_ADMIN, Constants.PASSWORD).
                        contentType(ContentType.JSON).
                        delete(String.valueOf(ID))                 ;
        return response;
    }
    public Response getBook(int ID) {
        Response response =
                RestAssured.given().
                        spec(bookURL).
                        auth().
                        preemptive().
                        basic(Constants.USERNAME_ADMIN, Constants.PASSWORD).
                        contentType(ContentType.JSON).
                        get(String.valueOf(ID))                 ;
        return response;
    }
    public Response getAllBooks() {
        Response response =
                RestAssured.given().
                        spec(bookURL).
                        auth().
                        preemptive().
                        basic(Constants.USERNAME_ADMIN, Constants.PASSWORD).
                        contentType(ContentType.JSON).
                        get()                 ;
        return response;
    }
}
