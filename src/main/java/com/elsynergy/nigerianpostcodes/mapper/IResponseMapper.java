package com.elsynergy.nigerianpostcodes.mapper;

public interface IResponseMapper<FROM, TO>
{
    public TO map(FROM toMap);
}
