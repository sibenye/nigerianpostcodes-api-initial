package service;

import com.elsynergy.nigerianpostcodes.mapper.LocalGovernmentAreaResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.StateResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.repo.geographyentities.LocalGovernmentAreaRepository;
import com.elsynergy.nigerianpostcodes.repo.geographyentities.StateRepository;
import com.elsynergy.nigerianpostcodes.service.geographyentities.GeographyService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GeographyServiceTest
{
    @Mock
    private StateRepository stateRepository;

    @Mock
    private StateResponseMapper stateResponseMapper;

    @Mock
    private LocalGovernmentAreaResponseMapper localGovernmentAreaResponseMapper;

    @Mock
    private LocalGovernmentAreaRepository localGovernmentAreaRepository;

    @InjectMocks
    private GeographyService geographyService;

    @Test
    public void testGetStates() throws ResourceNotFoundException
    {
        final State state1 = new State();
        state1.setCode("AB");
        state1.setName("Abia");
        state1.setId(1);

        final State state2 = new State();
        state2.setCode("IM");
        state2.setName("Imo");
        state2.setId(2);

        final List<State> states = new ArrayList<>();
        states.add(state1);
        states.add(state2);

        when(this.stateRepository.findAll()).thenReturn(states);

        this.geographyService.getStates(null);

        verify(this.stateRepository, times(1)).findAll();

        verify(this.stateResponseMapper, atLeastOnce()).map(any(State.class));
    }

    @Test
    public void testGetStatesWithStateCode() throws ResourceNotFoundException
    {
        final State state = new State();
        state.setCode("AB");
        state.setName("Abia");
        state.setId(1);


        final Optional<State> stateObjt = Optional.of(state);

        when(this.stateRepository.findOneByCode(anyString())).thenReturn(stateObjt);

        this.geographyService.getStates("AB");

        verify(this.stateRepository, times(1)).findOneByCode(anyString());

        verify(this.stateResponseMapper, times(1)).map(any(State.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetStatesWithStateCodeWillThrow() throws ResourceNotFoundException
    {
        final Optional<State> stateObjt = Optional.empty();

        when(this.stateRepository.findOneByCode(anyString())).thenReturn(stateObjt);

        this.geographyService.getStates("AB");

        verify(this.stateRepository, times(1)).findOneByCode(anyString());

        verify(this.stateResponseMapper, times(0)).map(any(State.class));
    }

}
