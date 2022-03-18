package com.restful_booker;

import org.json.JSONObject;

public class Book {
    public static JSONObject setbook(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname", "Element");
        jsonObject.put("lastname", "Test");
        jsonObject.put("totalprice", 5518);
        jsonObject.put("depositpaid", true);
        jsonObject.put("additionalneeds", "QA");
        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin", "2022-03-17");
        bookingDates.put("checkout", "2022-03-18");
        jsonObject.put("bookingdates", bookingDates);
        return jsonObject;
    }
}
