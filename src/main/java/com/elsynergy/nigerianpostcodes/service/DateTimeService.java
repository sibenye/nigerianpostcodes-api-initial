package com.elsynergy.nigerianpostcodes.service;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Component
public class DateTimeService implements IDateTimeService
{

    @Override
    public Calendar getCurrentDateAndTime(final TimeZone timeZone)
    {
        return GregorianCalendar.getInstance(timeZone);
    }

}
