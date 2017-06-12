package com.elsynergy.nigerianpostcodes.service;

import java.util.Calendar;
import java.util.TimeZone;

public interface IDateTimeService
{
    Calendar getCurrentDateAndTime(final TimeZone timeZone);
}
