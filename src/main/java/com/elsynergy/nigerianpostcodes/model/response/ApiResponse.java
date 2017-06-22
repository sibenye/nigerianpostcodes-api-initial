package com.elsynergy.nigerianpostcodes.model.response;

public class ApiResponse
{
    private static String SUCCESS_STATUS = "success";
    private static String FAIL_STATUS = "failure";
    private String status;

    private BaseResponse response;

    public ApiResponse(final BaseResponse response)
    {
        this.response = response;
        this.status = SUCCESS_STATUS;
    }

    public ApiResponse(final BaseResponse response, final boolean success)
    {
        this.response = response;
        this.status = success ? SUCCESS_STATUS : FAIL_STATUS;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus(final String status)
    {
        this.status = status;
    }

    public BaseResponse getResponse()
    {
        return this.response;
    }

    public void setResponse(final BaseResponse response)
    {
        this.response = response;
    }

}
