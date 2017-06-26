package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.response.StateResponse;

import org.springframework.stereotype.Component;

/**
 * Maps State to StateResponse
 * @author silver.ibenye
 *
 */
@Component
public class StateResponseMapper implements IResponseMapper<State, StateResponse>
{

    @Override
    public StateResponse map(final State toMap)
    {
        final StateResponse stateResponse = new StateResponse();

        stateResponse.setStateCode(toMap.getCode());
        stateResponse.setStateName(toMap.getName());
        return stateResponse;
    }

}
