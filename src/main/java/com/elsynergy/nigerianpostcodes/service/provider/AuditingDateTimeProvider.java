package com.elsynergy.nigerianpostcodes.service.provider;

import com.elsynergy.nigerianpostcodes.service.IDateTimeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.TimeZone;

@Component
public class AuditingDateTimeProvider implements DateTimeProvider
{
    @Autowired
    private final IDateTimeService dateTimeService;

    public AuditingDateTimeProvider(final IDateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    @Override
    public Calendar getNow()
    {
        return this.dateTimeService.getCurrentDateAndTime(TimeZone.getTimeZone("Africa/Lagos"));
    }

}
