package org.motechproject.ananya.bbc.domain;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class DateTest {
    @Test
    public void shouldGiveTheDateTimeEquivalentOfDayOfMonthAndYear(){
        String dateTimeEquivalent = new Date() {
            {
                setDay(86);
                setYear(2012);
            }
        }.getDateTimeEquivalent();

        assertEquals("26/03/2012", dateTimeEquivalent);
    }
}

