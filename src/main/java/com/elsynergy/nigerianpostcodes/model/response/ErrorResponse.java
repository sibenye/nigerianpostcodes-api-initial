package com.elsynergy.nigerianpostcodes.model.response;

import java.util.Arrays;
import java.util.List;

public class ErrorResponse extends BaseResponse
{
    private final Integer errorCode;

    private final String message;

    private final List<String> messageDetails;

    public ErrorResponse(final Integer errorCode, final String message, final List<String> messageDetails)
    {
        this.errorCode = errorCode;
        this.message = message;
        this.messageDetails = messageDetails;
    }

    public ErrorResponse(final Integer errorCode, final String message, final String messageDetail)
    {
        this.errorCode = errorCode;
        this.message = message;
        this.messageDetails = Arrays.asList(messageDetail);
    }

    public Integer getErrorCode()
    {
        return this.errorCode;
    }

    public String getMessage()
    {
        return this.message;
    }

    public List<String> getMessageDetails()
    {
        return this.messageDetails;
    }

}
