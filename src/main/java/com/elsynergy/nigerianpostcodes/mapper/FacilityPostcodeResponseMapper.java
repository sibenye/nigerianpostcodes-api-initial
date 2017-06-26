package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.response.FacilityPostcodeResponse;

import org.springframework.stereotype.Component;

/**
 * Maps FacilityPostcode to FacilityPostcodeResponse.
 *
 * @author silver.ibenye
 *
 */
@Component
public class FacilityPostcodeResponseMapper implements IResponseMapper<FacilityPostcode, FacilityPostcodeResponse>
{

    @Override
    public FacilityPostcodeResponse map(final FacilityPostcode toMap)
    {
        final FacilityPostcodeResponse out = new FacilityPostcodeResponse();
        out.setFacilityName(toMap.getFacility());
        out.setPostcode(toMap.getPostcode());
        out.setLocalGovernmentAreaName(toMap.getLocalGovernmentArea().getName());
        out.setStateCode(toMap.getLocalGovernmentArea().getState().getCode());
        out.setStateName(toMap.getLocalGovernmentArea().getState().getName());
        return out;
    }

}
