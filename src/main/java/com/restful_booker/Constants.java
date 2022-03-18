package com.restful_booker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Constants {
    public static String URL = "https://restful-booker.herokuapp.com/";
    public static String USERNAME_ADMIN = "admin";
    public static String PASSWORD = "password123";
    public static String CURRENT_TIME;

    static {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        CURRENT_TIME = dtf.format(now);
    }

}