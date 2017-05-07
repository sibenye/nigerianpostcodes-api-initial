package com.elsynergy.nigerianpostcodes.service;

import com.elsynergy.nigerianpostcodes.model.DAO.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.repo.FindRepo;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
/**
 * Postcode Search Service.
 *
 * @author silver.ibenye
 *
 */
public class FindService
{
    @Autowired
    private FindRepo postcodeSearchRepo;

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
    public List<RuralPostcode> getRuralPostcodes(final Integer stateId, final Integer lgaId, final String district, final String town) throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = this.postcodeSearchRepo.getRuralPostcodes(stateId, lgaId, district, town);

        if (ruralPostcodes.size() == 0) {
            throw new ResourceNotFoundException("No postcodes found");
        }

        return ruralPostcodes;
    }

    /**
     * Retrieve urban postcodes.
     *
     * @param stateId
     * @param town
     * @param street
     * @param area
     * @return List<UrbanPostcode>
     * @throws ResourceNotFoundException
     */
    public List<UrbanPostcode> getUrbanPostcodes(final Integer stateId, final String town, final String area, final String street) throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = this.postcodeSearchRepo.getUrbanPostcodes(stateId, town, area, street);

        if (urbanPostcodes.size() == 0) {
            throw new ResourceNotFoundException("No postcodes found");
        }

        return urbanPostcodes;
    }

    /**
     * Retrieve facility postcodes.
     *
     * @param stateId
     * @param lgaId
     * @param facility
     * @return List<FacilityPostcode>
     * @throws ResourceNotFoundException
     */
    public List<FacilityPostcode> getFacilityPostcodes(final Integer stateId, final Integer lgaId, final String facility) throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = this.postcodeSearchRepo.getFacilityPostcodes(stateId, lgaId, facility);

        if (facilityPostcodes.size() == 0) {
            throw new ResourceNotFoundException("No postcodes found");
        }

        return facilityPostcodes;
    }



}
