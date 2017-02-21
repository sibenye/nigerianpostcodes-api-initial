package com.elsynergy.nigerianpostcodes.web.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends ApiException
{
    private static Integer CODE = 404;

    private static HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public ResourceNotFoundException()
    {
        super(CODE, HTTP_STATUS);
    }

    public ResourceNotFoundException(final String messageDetail)
    {
        super(CODE, messageDetail, HTTP_STATUS);
    }


}
