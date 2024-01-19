package com.mashreq.confbooking.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {
    public static String[] possibleDateFormats = {
                    "yyyy-MM-dd",
                    "dd-MM-yyyy",
                    "MM-dd-yyyy",
                    "dd/MM/yyyy"
            };

   public static String BOOKING_DATE_FORMAT = "yyyy-MM-dd";

    public static LocalDate getDate(String inputDate) {
        try {
            Date date = org.apache.commons.lang3.time.DateUtils.parseDate(inputDate, possibleDateFormats);
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static LocalDateTime getLocalDateTimeFromTime(String time) {
        return LocalDate.now().atTime(LocalTime.parse(time));
    }

    public static String getStringDate(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
