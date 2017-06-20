package com.elsynergy.nigerianpostcodes.service;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 *
 * @author silver.ibenye
 *
 */
@Component
public class DateTimeService implements IDateTimeService
{
    private static String DEFAULT_FORMAT = "yyyy-MM-dd";
    private final TimeZone defaultTimeZone = TimeZone.getTimeZone("Africa/Lagos");

    @Override
    public Calendar getCurrentDateAndTime(final TimeZone timeZone)
    {
        return GregorianCalendar.getInstance(timeZone);
    }

    public Calendar getCurrentDateAndTime()
    {
        return this.getCurrentDateAndTime(this.defaultTimeZone);
    }

    public String getStringFormattedDate(final Date date)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }

}
