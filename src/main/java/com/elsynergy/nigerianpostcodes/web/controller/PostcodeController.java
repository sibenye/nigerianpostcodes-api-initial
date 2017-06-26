package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.response.ApiResponse;
import com.elsynergy.nigerianpostcodes.service.postcodeentities.PostcodeService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private PostcodeService postcodeService;

    @ApiOperation(value = "Retrieve Rural Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/rural-postcodes/{stateCode}")
    public @ResponseBody ApiResponse getRuralPostcodes(
            @Valid @PathVariable final String stateCode,
            @Valid @RequestParam ( required = false ) final String localGovtAreaName,
            @Valid @RequestParam ( required = false ) final String district,
            @Valid @RequestParam ( required = false ) final String town
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.postcodeService.getRuralPostcodes(stateCode, localGovtAreaName, district, town));
    }


    @ApiOperation(value = "Retrieve Urban Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/urban-postcodes/{stateCode}")
    public @ResponseBody ApiResponse getUrbanPostcodes(
            @Valid @PathVariable final String stateCode,
            @Valid @RequestParam ( required = false ) final String town,
            @Valid @RequestParam ( required = false ) final String area,
            @Valid @RequestParam  ( required = false ) final String street
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.postcodeService.getUrbanPostcodes(stateCode, town, area, street));
    }

    @ApiOperation(value = "Retrieve Facility Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/facility-postcodes/{stateCode}")
    public @ResponseBody ApiResponse getFacilityPostcodes(
            @Valid @PathVariable final String stateCode,
            @Valid @RequestParam ( required = false ) final String localGovtAreaName,
            @Valid @RequestParam ( required = false ) final String facilityName
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.postcodeService.getFacilityPostcodes(stateCode, localGovtAreaName, facilityName));
    }

}
