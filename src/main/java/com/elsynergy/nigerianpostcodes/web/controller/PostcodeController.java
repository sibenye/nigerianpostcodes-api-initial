package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.DAO.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.model.response.ApiResponse;
import com.elsynergy.nigerianpostcodes.service.FindService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/postcodes")
/**
 * Postcode Controller.
 *
 * @author silver.ibenye
 *
 */
public class PostcodeController
{
    @Autowired
    private FindService findService;

    @ApiOperation(value = "Retrieve Rural Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/rural-postcodes/{stateId}")
    public @ResponseBody ApiResponse getRuralPostcodes(
            @Valid @PathVariable final Integer stateId,
            @Valid @RequestParam ( required = false ) final Integer lgaId,
            @Valid @RequestParam ( required = false ) final String district,
            @Valid @RequestParam ( required = false ) final String town
            ) throws ResourceNotFoundException
    {
        final List<RuralPostcode> result = this.findService.getRuralPostcodes(stateId, lgaId, district, town);
        return new ApiResponse(result);
    }


    @ApiOperation(value = "Retrieve Urban Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/urban-postcodes/{stateId}")
    public @ResponseBody ApiResponse getUrbanPostcodes(
            @Valid @PathVariable final Integer stateId,
            @Valid @RequestParam ( required = false ) final String town,
            @Valid @RequestParam ( required = false ) final String area,
            @Valid @RequestParam  ( required = false ) final String street
            ) throws ResourceNotFoundException
    {
        final List<UrbanPostcode> result = this.findService.getUrbanPostcodes(stateId, town, area, street);
        return new ApiResponse(result);
    }

    @ApiOperation(value = "Retrieve Facility Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/facility-postcodes/{stateId}")
    public @ResponseBody ApiResponse getFacilityPostcodes(
            @Valid @PathVariable final Integer stateId,
            @Valid @RequestParam ( required = false ) final Integer lgaId,
            @Valid @RequestParam ( required = false ) final String facility
            ) throws ResourceNotFoundException
    {
        final List<FacilityPostcode> result = this.findService.getFacilityPostcodes(stateId, lgaId, facility);
        return new ApiResponse(result);
    }

}
