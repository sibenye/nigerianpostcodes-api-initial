package com.elsynergy.nigerianpostcodes.web.controller;

import com.elsynergy.nigerianpostcodes.model.response.ApiResponse;
import com.elsynergy.nigerianpostcodes.service.FindService;
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
    private FindService findService;

    @ApiOperation(value = "Retrieve States.")
    @RequestMapping(method = RequestMethod.GET, value = "/states")
    public @ResponseBody ApiResponse getStates(
            @Valid @RequestParam ( required = false ) final Integer stateId
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.findService.getStates(stateId));
    }

    @ApiOperation(value = "Retrieve LGAs.")
    @RequestMapping(method = RequestMethod.GET, value = "/states/{stateId}/lgas")
    public @ResponseBody ApiResponse getLGAs(
            @Valid @PathVariable final Integer stateId,
            @Valid @RequestParam ( required = false ) final Integer lgaId
            ) throws ResourceNotFoundException
    {
        return new ApiResponse(this.findService.getLGAs(stateId, lgaId));
    }

}
