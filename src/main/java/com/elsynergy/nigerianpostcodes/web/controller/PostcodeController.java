package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.request.FacilityPostcodeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RuralPostcodeRequest;
import com.elsynergy.nigerianpostcodes.model.request.UrbanPostcodeRequest;
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
    @RequestMapping(method = RequestMethod.GET, value = "/rural-postcodes")
    public @ResponseBody ApiResponse getRuralPostcodes(
            @Valid @ModelAttribute final RuralPostcodeRequest request
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.postcodeService.getRuralPostcodes(request));
    }


    @ApiOperation(value = "Retrieve Urban Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/urban-postcodes")
    public @ResponseBody ApiResponse getUrbanPostcodes(
            @Valid @ModelAttribute final UrbanPostcodeRequest request
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.postcodeService.getUrbanPostcodes(request));
    }

    @ApiOperation(value = "Retrieve Facility Postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/facility-postcodes")
    public @ResponseBody ApiResponse getFacilityPostcodes(
            @Valid @ModelAttribute final FacilityPostcodeRequest request
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.postcodeService.getFacilityPostcodes(request));
    }

}
