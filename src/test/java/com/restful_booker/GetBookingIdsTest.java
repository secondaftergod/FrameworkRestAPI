package com.restful_booker;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class GetBookingIdsTest extends BookerBase {

    @Test
    public void getAllBookingId()
    {
        Response response = getAllBooks();
        response.print();
        Assert.assertEquals(response.getStatusCode(), 200, "Response is not 200.");

        List<Object> bookingIds = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIds.isEmpty(), "List of booking Ids is empty when it shouldn't be.");
    }

    @Test
    public void getBookById()
    {
        Response responseCreate = createNewBooking(Book.setbook());
        responseCreate.prettyPrint();
        int newID = responseCreate.jsonPath().getInt("bookingid");
        Response response = getBook(newID);

        response.print();

        Assert.assertEquals(response.getStatusCode(), 200, "Response is not 200.");

        String name = response.jsonPath().get("firstname");
        String lastName = response.jsonPath().get("lastname");
        int totalPrice = response.jsonPath().getInt("totalprice");
        String checkin = response.jsonPath().getString("bookingdates.checkin");
        String checkout = response.jsonPath().getString("bookingdates.checkout");


        SoftAssert softAss = new SoftAssert();
        softAss.assertEquals(name, "Element", "Name is not as expected.");
        softAss.assertEquals(lastName, "Test", "Last name is not as expected.");
        softAss.assertEquals(totalPrice, 5518, "Total price not as expected.");
        softAss.assertEquals(checkin, "2022-03-17", "Total price not as expected.");
        softAss.assertEquals(checkout, "2022-03-18", "Total price not as expected.");

        softAss.assertAll();
    }
}
