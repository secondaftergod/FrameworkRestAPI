package com.restful_booker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;


@Feature("Создание и обновления книги")
@Owner("Element")
public class CreateAndUpdateTest extends  BookerBase
{
    @Test(description = "Create Book")
    public void createBookingTest()
   {
    Response responseCreate = createNewBooking(Book.setbook());
    responseCreate.prettyPrint();
    Assert.assertEquals(responseCreate.getStatusCode(), 200, "Response is not 200.");   }

    @Test
    public void updateBookingByPutTest()
    {
        Response responseCreate = createNewBooking(Book.setbook());
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
        jsonObjectUpdate.put("bookingdates", bookingDates2);

        Response responseUpdate= updateBookPut(jsonObjectUpdate,newID);
        responseUpdate.prettyPrint();
        Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response is not 200.");
    }

 @Test
 public void updateBookingByPatchTest()
 {
     Response responseCreate = createNewBooking(Book.setbook());
     responseCreate.prettyPrint();

     int newID = responseCreate.jsonPath().getInt("bookingid");

     JSONObject jsonObjectUpdate = new JSONObject();
     jsonObjectUpdate.put("lastname", "Second");
     jsonObjectUpdate.put("depositpaid", false);

     Response responseUpdate= updateBookPatch(jsonObjectUpdate,newID);
     responseUpdate.prettyPrint();
     Assert.assertEquals(responseUpdate.getStatusCode(), 200, "Response is not 200.");
 }
}
