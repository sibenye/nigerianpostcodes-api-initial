package com.elsynergy.nigerianpostcodes.service;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public interface IDateTimeService
{
    public Calendar getCurrentDateAndTime(final TimeZone timeZone);

    public Calendar getCurrentDateAndTime();

    public String getStringFormattedDate(final Date date);
}
