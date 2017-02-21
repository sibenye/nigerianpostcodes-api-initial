package com.elsynergy.nigerianpostcodes.web.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class BadRequestException extends ApiException
{
    private static Integer CODE = 100;

    private static HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public BadRequestException()
    {
        super(CODE, HTTP_STATUS);
    }

    public BadRequestException(final String messageDetail)
    {
        super(CODE, messageDetail, HTTP_STATUS);
    }

}
