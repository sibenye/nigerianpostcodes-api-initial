package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.response.RuralPostcodeResponse;

import org.springframework.stereotype.Component;

/**
 * Maps RuralPostcode to RuralPostcodeResponse;
 *
 * @author silver.ibenye
 *
 */
@Component
public class RuralPostcodeResponseMapper implements IResponseMapper<RuralPostcode, RuralPostcodeResponse>
{

    @Override
    public RuralPostcodeResponse map(final RuralPostcode toMap)
    {
        final RuralPostcodeResponse out = new RuralPostcodeResponse();
        out.setDistrict(toMap.getDistrict());
        out.setTown(toMap.getTown());
        out.setPostcode(toMap.getPostcode());
        out.setLocalGovernmentAreaName(toMap.getLocalGovernmentArea().getName());
        out.setStateCode(toMap.getLocalGovernmentArea().getState().getCode());
        out.setStateName(toMap.getLocalGovernmentArea().getState().getName());
        return out;
    }

}
