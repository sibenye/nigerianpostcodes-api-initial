package com.elsynergy.nigerianpostcodes.service.geographyentities;

import com.elsynergy.nigerianpostcodes.mapper.LocalGovernmentAreaResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.StateResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.response.ApiFindResponse;
import com.elsynergy.nigerianpostcodes.model.response.LocalGovernmentAreaResponse;
import com.elsynergy.nigerianpostcodes.model.response.StateResponse;
import com.elsynergy.nigerianpostcodes.repo.geographyentities.LocalGovernmentAreaRepository;
import com.elsynergy.nigerianpostcodes.repo.geographyentities.StateRepository;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author silver.ibenye
 *
 */
@Service
public class GeographyService
{
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private StateResponseMapper stateResponseMapper;

    @Autowired
    private LocalGovernmentAreaResponseMapper localGovernmentAreaResponseMapper;

    @Autowired
    private LocalGovernmentAreaRepository localGovernmentAreaRepository;

    /**
     * Retrieve states/state.
     *
     * @param stateCode
     * @return
     * @throws ResourceNotFoundException
     */
    public ApiFindResponse getStates(final String stateCode) throws ResourceNotFoundException
    {
        final List<StateResponse> stateResponses = new ArrayList<>();
        if (stateCode != null) {
            final Optional<State> state = this.stateRepository.findOneByCode(stateCode);
            if (!state.isPresent()) {
                throw new ResourceNotFoundException("StateCode: " + stateCode + " Was Not Found.");
            }
            stateResponses.add(this.stateResponseMapper.map(state.get()));
        } else {
            final Iterable<State> states = this.stateRepository.findAll();
            for (final State state : states) {
                stateResponses.add(this.stateResponseMapper.map(state));
            }
        }
        return new ApiFindResponse(stateResponses);
    }

    public ApiFindResponse getLocalGovernmentArea(final String stateCode, final String localGovtAreaName) throws ResourceNotFoundException
    {
        List<LocalGovernmentArea> localGovernmentAreas = new ArrayList<>();

        if (stateCode != null && localGovtAreaName != null) {
            localGovernmentAreas = this.localGovernmentAreaRepository.findByStateCodeAndName(stateCode, localGovtAreaName);
        } else {
            localGovernmentAreas = this.localGovernmentAreaRepository.findByStateCode(stateCode);
        }

        if (localGovernmentAreas.size() == 0) {
            throw new ResourceNotFoundException();
        }

        final List<LocalGovernmentAreaResponse> localGovernmentAreaResponses = new ArrayList<>();

        for (final LocalGovernmentArea lga : localGovernmentAreas) {
            localGovernmentAreaResponses.add(this.localGovernmentAreaResponseMapper.map(lga));
        }

        return new ApiFindResponse(localGovernmentAreaResponses);
    }


}
