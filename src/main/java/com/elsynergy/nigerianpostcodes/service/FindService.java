package com.elsynergy.nigerianpostcodes.service;

import com.elsynergy.nigerianpostcodes.model.DAO.*;
import com.elsynergy.nigerianpostcodes.model.response.ApiFindResponse;
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
    private FindRepo postcodeFindRepo;

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
    public ApiFindResponse getRuralPostcodes(final Integer stateId, final Integer lgaId, final String district, final String town) throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = this.postcodeFindRepo.getRuralPostcodes(stateId, lgaId, district, town);

        if (ruralPostcodes.size() == 0) {
            final String message = (lgaId == null && district == null && town == null) ? "No Rural Postcodes Found For Selected State."
                    : (district == null && town == null) ? "No Rural Postcodes Found For Selected LGA."
                            : town == null ? "No Rural Postcodes Found For Selected District."
                                    : "No Postcode Found For Selected Town.";
            throw new ResourceNotFoundException(message);
        }

        return new ApiFindResponse(ruralPostcodes);
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
    public ApiFindResponse getUrbanPostcodes(final Integer stateId, final String town, final String area, final String street) throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = this.postcodeFindRepo.getUrbanPostcodes(stateId, town, area, street);

        if (urbanPostcodes.size() == 0) {
            final String message = (town == null && area == null && street == null) ? "No Urban Postcodes Found For Selected State."
                    : (area == null && street == null) ? "No Urban Postcodes Found For Selected Town."
                            : street == null ? "No Urban Postcodes Found For Selected Area" : "No Postcode Found For Selected Street.";
            throw new ResourceNotFoundException(message);
        }

        return new ApiFindResponse(urbanPostcodes);
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
    public ApiFindResponse getFacilityPostcodes(final Integer stateId, final Integer lgaId, final String facility) throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = this.postcodeFindRepo.getFacilityPostcodes(stateId, lgaId, facility);

        if (facilityPostcodes.size() == 0) {
            final String message = (lgaId == null && facility == null) ? "No Facility Postcodes Found For The Selected State."
                    : (facility == null) ? "No Facility Postcodes Found For Selected LGA."
                            : "No Postcode Found For Selected Facility.";
            throw new ResourceNotFoundException(message);
        }

        return new ApiFindResponse(facilityPostcodes);
    }

    /**
     * Retrieve States.
     *
     * @param stateId
     * @return List<State>
     * @throws ResourceNotFoundException
     */
    public ApiFindResponse getStates(final Integer stateId) throws ResourceNotFoundException
    {
        final List<State> states = this.postcodeFindRepo.getStates(stateId);

        if (states.size() == 0) {
            final String message = stateId == null ? "No States Found." : "StateId: " + stateId + " Was Not Found.";
            throw new ResourceNotFoundException(message);
        }

        return new ApiFindResponse(states);
    }

    /**
     * Retrieve LGAs.
     *
     * @param stateId
     * @param lgaId
     * @return
     * @throws ResourceNotFoundException
     */
    public ApiFindResponse getLGAs(final Integer stateId, final Integer lgaId) throws ResourceNotFoundException
    {
        final List<LGA> lgas = this.postcodeFindRepo.getLGAs(stateId, lgaId);

        if (lgas.size() == 0) {
            final String message = lgaId == null ? "No LGAs Found For stateId: "+ stateId + "." : "lgaId: " + lgaId + " Was Not Found.";
            throw new ResourceNotFoundException(message);
        }

        return new ApiFindResponse(lgas);
    }


}
