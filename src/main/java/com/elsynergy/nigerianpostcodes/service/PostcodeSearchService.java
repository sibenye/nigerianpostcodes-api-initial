package com.elsynergy.nigerianpostcodes.service;

import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.repo.PostcodeSearchRepo;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostcodeSearchService
{
    @Autowired
    private PostcodeSearchRepo postcodeSearchRepo;

    /**
     * Retrieve rural postcodes.
     *
     * @param state
     * @param lga
     * @param town
     * @param district
     * @return List<RuralPostcode>
     * @throws ResourceNotFoundException
     */
    public List<RuralPostcode> getRuralPostcodes(final String state, final String lga, final String town, final String district) throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = this.postcodeSearchRepo.getRuralPostcodes(state, lga, town, district);

        if (ruralPostcodes.size() == 0) {
            throw new ResourceNotFoundException("No postcodes found");
        }

        return ruralPostcodes;
    }

    /**
     * Retrieve urban postcodes.
     *
     * @param state
     * @param town
     * @param street
     * @param area
     * @return List<UrbanPostcode>
     * @throws ResourceNotFoundException
     */
    public List<UrbanPostcode> getUrbanPostcodes(final String state, final String town, final String street, final String area) throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = this.postcodeSearchRepo.getUrbanPostcodes(state, town, street, area);

        if (urbanPostcodes.size() == 0) {
            throw new ResourceNotFoundException("No postcodes found");
        }

        return urbanPostcodes;
    }

}
