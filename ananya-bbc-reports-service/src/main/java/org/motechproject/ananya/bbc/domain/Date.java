package org.motechproject.ananya.bbc.domain;

import org.joda.time.DateTime;

import java.io.Serializable;

public class Date implements Serializable{
    private Integer day;
    private Integer year;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDateTimeEquivalent(){

        DateTime dateTime = new DateTime();
        dateTime.withYear(year);
        dateTime.withDayOfYear(day);
        return dateTime.toString("dd/mm/yyyy");
    }
}
