package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.response.LocalGovernmentAreaResponse;

import org.springframework.stereotype.Component;

/**
 * Maps LocalGovernmentArea to LocalGovernmentAreaResponse
 * @author silver.ibenye
 *
 */
@Component
public class LocalGovernmentAreaResponseMapper implements IResponseMapper<LocalGovernmentArea, LocalGovernmentAreaResponse>
{

    @Override
    public LocalGovernmentAreaResponse map(final LocalGovernmentArea toMap)
    {
        final LocalGovernmentAreaResponse localGovernmentAreaResponse = new LocalGovernmentAreaResponse();
        localGovernmentAreaResponse.setLocalGovernmentAreaName(toMap.getName());
        localGovernmentAreaResponse.setStateCode(toMap.getState().getCode());
        localGovernmentAreaResponse.setStateName(toMap.getState().getName());
        return localGovernmentAreaResponse;
    }

}
