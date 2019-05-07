package com.mattiarubini.reminder;

import java.util.Date;

public class Reminder {

    private String title;
    private Date date;

    public Reminder(String title, Date date){
        this.title = title;
        this.date = date;
    }

    @Override
    public String toString() {
        return this.title + "\n" + this.date.toString();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
