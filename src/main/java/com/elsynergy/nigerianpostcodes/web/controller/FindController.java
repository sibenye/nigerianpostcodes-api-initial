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
@RequestMapping("/find")
/**
 * Postcode Search Controller.
 *
 * @author silver.ibenye
 *
 */
public class FindController
{
    @Autowired
    private FindService findService;

    @ApiOperation(value = "Retrieve rural postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/rural-postcodes/{stateId}/{lgaId}")
    public @ResponseBody ApiResponse getRuralPostcodes(
            @Valid @PathVariable final Integer stateId,
            @Valid @PathVariable final Integer lgaId,
            @Valid @RequestParam ( required = false ) final String town,
            @Valid @RequestParam ( required = false ) final String district
            ) throws ResourceNotFoundException
    {
        final List<RuralPostcode> result = this.findService.getRuralPostcodes(stateId, lgaId, town, district);
        return new ApiResponse(result);
    }


    @ApiOperation(value = "Retrieve urban postcodes.")
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

    @ApiOperation(value = "Retrieve facility postcodes.")
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
