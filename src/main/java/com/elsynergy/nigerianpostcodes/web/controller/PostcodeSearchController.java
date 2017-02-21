package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.service.PostcodeSearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/postcodes")
public class PostcodeSearchController
{
    @Autowired
    private PostcodeSearchService postcodeSearchService;

    @RequestMapping(method = RequestMethod.GET, value = "/rural/{state}")
    public @ResponseBody List<RuralPostcode> get(
            @Valid @PathVariable final String state,
            @Valid @RequestParam  ( required = false ) final String lga,
            @Valid @RequestParam ( required = false ) final String town,
            @Valid @RequestParam ( required = false ) final String district
            )
    {
        return this.postcodeSearchService.getRuralPostcodes(state, lga, town, district);
    }

}
