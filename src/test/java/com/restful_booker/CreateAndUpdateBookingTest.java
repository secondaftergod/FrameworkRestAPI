package com.restful_booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateAndUpdateBookingTest extends  BookerBase
{
  @Test
   public void createBookingTest()
   {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("firstname", "Ostap");
    jsonObject.put("lastname", "Test");
    jsonObject.put("totalprice", 5518);
    jsonObject.put("depositpaid", true);
    jsonObject.put("additionalneeds", "QA");
    JSONObject bookingDates = new JSONObject();
    bookingDates.put("checkin", "2022-03-17");
    bookingDates.put("checkout", "2022-03-18");
    jsonObject.put("bookingdates", bookingDates);

    Response responseCreate = createNewBooking(jsonObject);
    responseCreate.prettyPrint();
    Assert.assertEquals(responseCreate.getStatusCode(), 200, "Response is not 200.");   }

    @Test
    public void updateBookingByPutTest()
    {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Ostap");
        jsonObject.put("lastname", "Test");
        jsonObject.put("totalprice", 5518);
        jsonObject.put("depositpaid", true);
        jsonObject.put("additionalneeds", "QA");
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2022-03-17");
        bookingDates.put("checkout", "2022-03-18");
        jsonObject.put("bookingdates", bookingDates);

        Response responseCreate = createNewBooking(jsonObject);
        responseCreate.prettyPrint();

        int newID = responseCreate.jsonPath().getInt("bookingid");

        JSONObject jsonObjectUpdate = new JSONObject();
        jsonObjectUpdate.put("firstname", "element");
        jsonObjectUpdate.put("lastname", "TestMatick");
        jsonObjectUpdate.put("totalprice", 111);
        jsonObjectUpdate.put("depositpaid", false);
        jsonObjectUpdate.put("additionalneeds", "qa");
        JSONObject bookingDates2 = new JSONObject();
        bookingDates2.put("checkin", "2022-03-20");
        bookingDates2.put("checkout", "2022-02-21");
        jsonObjectUpdate.put("bookingdates", bookingDates);

        System.out.println(String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
        Response responseUpdate = RestAssured.given().
                auth().preemptive().basic("admin", "password123").
                        contentType(ContentType.JSON).body(jsonObjectUpdate.toString()).put( String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
        responseUpdate.prettyPrint();

        // verify response is 200
        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response is not 200.");
    }

 @Test
 public void updateBookingByPatchTest()
 {
  JSONObject jsonObject = new JSONObject();
  jsonObject.put("firstname", "Naeem");
  jsonObject.put("lastname", "Mal");
  jsonObject.put("totalprice", 111);
  jsonObject.put("depositpaid", true);
  jsonObject.put("additionalneeds", "Baby Chair");
  JSONObject bookingDates = new JSONObject();
  bookingDates.put("checkin", "2022-02-01");
  bookingDates.put("checkout", "2022-02-03");
  jsonObject.put("bookingdates", bookingDates);

  Response responseCreate = createNewBooking(jsonObject);
  responseCreate.prettyPrint();

  int newID = responseCreate.jsonPath().getInt("bookingid");

  JSONObject jsonObjectUpdate = new JSONObject();
  jsonObjectUpdate.put("lastname", "Mlx");
  jsonObjectUpdate.put("depositpaid", false);

  System.out.println(String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
  Response responseUpdate = RestAssured.given().
          auth().preemptive().basic("admin", "password123").
          contentType(ContentType.JSON).body(jsonObjectUpdate.toString()).patch( String.format("https://restful-booker.herokuapp.com/booking/%s", newID));
  responseUpdate.prettyPrint();

  // verify response is 200
  Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response is not 200.");
 }
}
