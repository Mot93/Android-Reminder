package com.mattiarubini.reminder;

import java.util.Date;

/**
 * Reminder is a container used as an interface with the database
 * Is used to populate the Views with the methods getText and getTrigger
 * */
public class Reminder {

    private String title;
    private Date date;

    // TODO: need more constructor for more variant: bullet list instead of title & location instead of date
    public Reminder(String title, Date date){
        this.title = title;
        this.date = date;
    }

    /**
     * getText return a string to place in the reminder_text in the reminder_layout.xml
     * */
    public String getText(){
        return this.title;
    }

    /**
     * getTrigger return a string to place in the reminder_trigger in the reminder_layout.xml
     * */
    public String getTrigger(){
        return this.date.toString();
    }

} // class Reminder
