package com.elsynergy.nigerianpostcodes.model.Response;

import java.util.Arrays;
import java.util.List;

public class ApiErrorResponse
{
    private final Integer errorCode;

    private final String message;

    private final List<String> messageDetails;

    public ApiErrorResponse(final Integer errorCode, final String message, final List<String> messageDetails)
    {
        this.errorCode = errorCode;
        this.message = message;
        this.messageDetails = messageDetails;
    }

    public ApiErrorResponse(final Integer errorCode, final String message, final String messageDetail)
    {
        this.errorCode = errorCode;
        this.message = message;
        this.messageDetails = Arrays.asList(messageDetail);
    }

    public Integer getErrorCode()
    {
        return errorCode;
    }

    public String getMessage()
    {
        return message;
    }

    public List<String> getMessageDetails()
    {
        return messageDetails;
    }

}
