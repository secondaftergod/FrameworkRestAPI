package com.restful_booker;


import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteTest extends  BookerBase {

 @Test
 public void deleteBookingTest()
 {
   Response responseCreate = createNewBooking(Book.setbook());
   responseCreate.prettyPrint();

   int newID = responseCreate.jsonPath().getInt("bookingid");
   Response responseDelete = deleteBook(newID);

   Assert.assertEquals(responseDelete.getStatusCode(), 201, "Response is not 200.");

   Response responseAfterDelete = getBook(newID);
   System.out.print(responseAfterDelete.body().asString());
   Assert.assertEquals(responseAfterDelete.body().asString(), "Not Found", "Object not deleted.");
 }
}
