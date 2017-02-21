package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.service.PostcodeSearchService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/postcodes")
public class PostcodeSearchController
{
    @Autowired
    private PostcodeSearchService postcodeSearchService;

    @ApiOperation(value = "Retrieve rural postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/rural/{state}")
    public @ResponseBody List<RuralPostcode> getRuralPostcodes(
            @Valid @PathVariable final String state,
            @Valid @RequestParam  ( required = false ) final String lga,
            @Valid @RequestParam ( required = false ) final String town,
            @Valid @RequestParam ( required = false ) final String district
            ) throws ResourceNotFoundException
    {
        return this.postcodeSearchService.getRuralPostcodes(state, lga, town, district);
    }


    @ApiOperation(value = "Retrieve urban postcodes.")
    @RequestMapping(method = RequestMethod.GET, value = "/urban/{state}")
    public @ResponseBody List<UrbanPostcode> getUrbanPostcodes(
            @Valid @PathVariable final String state,
            @Valid @RequestParam ( required = false ) final String town,
            @Valid @RequestParam  ( required = false ) final String street,
            @Valid @RequestParam ( required = false ) final String area
            ) throws ResourceNotFoundException
    {
        return this.postcodeSearchService.getUrbanPostcodes(state, town, street, area);
    }

}
