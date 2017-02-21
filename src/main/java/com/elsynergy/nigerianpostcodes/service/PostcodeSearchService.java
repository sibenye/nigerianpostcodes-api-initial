package com.elsynergy.nigerianpostcodes.service;

import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.repo.PostcodeSearchRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostcodeSearchService
{
    @Autowired
    private PostcodeSearchRepo postcodeSearchRepo;

    public List<RuralPostcode> getRuralPostcodes(final String state, final String lga, final String town, final String district)
    {
        return this.postcodeSearchRepo.GetRuralPostcodes(state, lga, town, district);
    }

}
