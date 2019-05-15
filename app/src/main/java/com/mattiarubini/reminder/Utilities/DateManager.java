package com.mattiarubini.reminder.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Transition String to Date and vice-versa
 * All strings and date must be in the format of yyyy/mm/dd hh:mm:ss
 * */
public class DateManager {

    /**
     * The format used across the project
     * yyyy/mm/dd hh:mm:ss
     * */
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd hh:mm:ss Z");

    /**
     * Given a date, returns a String in the form of yyyy/mm/dd hh:mm:ss
     * */
    public static String dateToString(Date date){
        return dateFormat.format(date);
    }

    /**
     * Given a string in the form of yyyy/mm/dd hh:mm:ss, returns a Date Object
     * */
    public static Date stringToDate(String dateString) throws ParseException {
        return dateFormat.parse(dateString);
    }

}
