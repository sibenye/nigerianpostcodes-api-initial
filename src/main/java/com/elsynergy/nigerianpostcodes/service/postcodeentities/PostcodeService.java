package com.elsynergy.nigerianpostcodes.service.postcodeentities;

import com.elsynergy.nigerianpostcodes.mapper.FacilityPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.RuralPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.UrbanPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.model.response.ApiFindResponse;
import com.elsynergy.nigerianpostcodes.model.response.FacilityPostcodeResponse;
import com.elsynergy.nigerianpostcodes.model.response.RuralPostcodeResponse;
import com.elsynergy.nigerianpostcodes.model.response.UrbanPostcodeResponse;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.FacilityPostcodeRepository;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.RuralPostcodeRepository;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.UrbanPostcodeRepository;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
@Service
public class PostcodeService
{
    @Autowired
    private FacilityPostcodeRepository facilityPostcodeRepository;

    @Autowired
    private RuralPostcodeRepository ruralPostcodeRepository;

    @Autowired
    private UrbanPostcodeRepository urbanPostcodeRepository;

    @Autowired
    private FacilityPostcodeResponseMapper facilityPostcodeResponseMapper;

    @Autowired
    private RuralPostcodeResponseMapper ruralPostcodeResponseMapper;

    @Autowired
    private UrbanPostcodeResponseMapper urbanPostcodeResponseMapper;

    public ApiFindResponse getFacilityPostcodes(final String stateCode, final String localGovtAreaName, final String facilityName) throws ResourceNotFoundException
    {
        List<FacilityPostcode> facilityPostcodes = new ArrayList<>();
        if (localGovtAreaName != null && facilityName != null) {
            facilityPostcodes = this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndFacility(stateCode, localGovtAreaName, facilityName);
        } else if (localGovtAreaName != null) {
            facilityPostcodes = this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(stateCode, localGovtAreaName);
        } else {
            facilityPostcodes = this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCode(stateCode);
        }

        if (facilityPostcodes.size() == 0) {
            throw new ResourceNotFoundException();
        }

        final List<FacilityPostcodeResponse> facilityPostcodeResponses = new ArrayList<>();

        for (final FacilityPostcode facilityPostcode : facilityPostcodes) {
            facilityPostcodeResponses.add(this.facilityPostcodeResponseMapper.map(facilityPostcode));
        }
        return new ApiFindResponse(facilityPostcodeResponses);
    }

    public ApiFindResponse getRuralPostcodes(final String stateCode, final String localGovtAreaName, final String district, final String town) throws ResourceNotFoundException
    {
        List<RuralPostcode> ruralPostcodes = new ArrayList<>();
        if (localGovtAreaName != null && district != null && town != null) {
            ruralPostcodes = this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrictAndTown(stateCode, localGovtAreaName, district, town);
        } else if (localGovtAreaName != null && district != null) {
            ruralPostcodes = this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrict(stateCode, localGovtAreaName, district);
        } else if (localGovtAreaName != null) {
            ruralPostcodes = this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(stateCode, localGovtAreaName);
        } else {
            ruralPostcodes = this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCode(stateCode);
        }

        if (ruralPostcodes.size() == 0) {
            throw new ResourceNotFoundException();
        }

        final List<RuralPostcodeResponse> ruralPostcodeResponses = new ArrayList<>();

        for (final RuralPostcode ruralPostcode : ruralPostcodes) {
            ruralPostcodeResponses.add(this.ruralPostcodeResponseMapper.map(ruralPostcode));
        }
        return new ApiFindResponse(ruralPostcodeResponses);
    }

    public ApiFindResponse getUrbanPostcodes(final String stateCode, final String town, final String area, final String street) throws ResourceNotFoundException
    {
        List<UrbanPostcode> urbanPostcodes = new ArrayList<>();
        if (town != null && area != null && street != null) {
            urbanPostcodes = this.urbanPostcodeRepository.findByStateCodeAndTownAndAreaAndStreet(stateCode, town, area, street);
        } else if (town != null && area != null) {
            urbanPostcodes = this.urbanPostcodeRepository.findByStateCodeAndTownAndArea(stateCode, town, area);
        } else if (town != null) {
            urbanPostcodes = this.urbanPostcodeRepository.findByStateCodeAndTown(stateCode, town);
        } else {
            urbanPostcodes = this.urbanPostcodeRepository.findByStateCode(stateCode);
        }

        if (urbanPostcodes.size() == 0) {
            throw new ResourceNotFoundException();
        }

        final List<UrbanPostcodeResponse> urbanPostcodeResponses = new ArrayList<>();

        for (final UrbanPostcode urbanPostcode : urbanPostcodes) {
            urbanPostcodeResponses.add(this.urbanPostcodeResponseMapper.map(urbanPostcode));
        }
        return new ApiFindResponse(urbanPostcodeResponses);
    }
}
