package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.response.ApiResponse;
import com.elsynergy.nigerianpostcodes.service.geographyentities.GeographyService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/geography")
/**
 * Geography Controller.
 *
 * @author silver.ibenye
 *
 */
public class GeographyController
{
    @Autowired
    private GeographyService geographyService;

    @ApiOperation(value = "Retrieve States.")
    @RequestMapping(method = RequestMethod.GET, value = "/states")
    public @ResponseBody ApiResponse getStates(
            @Valid @RequestParam ( required = false ) final String stateCode
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.geographyService.getStates(stateCode));
    }

    @ApiOperation(value = "Retrieve LGAs.")
    @RequestMapping(method = RequestMethod.GET, value = "/states/{stateCode}/lgas")
    public @ResponseBody ApiResponse getLGAs(
            @Valid @PathVariable final String stateCode,
            @Valid @RequestParam ( required = false ) final String localGovtAreaName
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.geographyService.getLocalGovernmentArea(stateCode, localGovtAreaName));
    }

}
