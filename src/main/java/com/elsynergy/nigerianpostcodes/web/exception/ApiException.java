package com.elsynergy.nigerianpostcodes.web.exception;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class ApiException extends Exception
{
    private final Integer errrCode;

    private final HttpStatus httpStatus;

    private String messageDetail = null;

    public ApiException()
    {
        super(CodeMessage.getMessage(555));
        this.errrCode = 555;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(final String messageDetail)
    {
        super(CodeMessage.getMessage(555));
        this.errrCode = 555;
        this.messageDetail = messageDetail;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(final Integer code)
    {
        super(CodeMessage.getMessage(code));
        this.errrCode = code;
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(final Integer code, final String messageDetail)
    {
        super(CodeMessage.getMessage(code));
        this.errrCode = code;
        this.setMessageDetail(messageDetail);
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public ApiException(final Integer code, final HttpStatus httpStatus)
    {
        super(CodeMessage.getMessage(code));
        this.errrCode = code;
        this.httpStatus = httpStatus;
    }

    public ApiException(final Integer code, final String messageDetail, final HttpStatus httpStatus)
    {
        super(CodeMessage.getMessage(code));
        this.errrCode = code;
        this.messageDetail = messageDetail;
        this.httpStatus = httpStatus;
    }

    public Integer getErrorCode()
    {
        return this.errrCode;
    }

    public HttpStatus getHttpStatus()
    {
        return this.httpStatus;
    }

    public String getMessageDetail()
    {
        return this.messageDetail;
    }

    public void setMessageDetail(final String messageDetail)
    {
        this.messageDetail = messageDetail;
    }

}
