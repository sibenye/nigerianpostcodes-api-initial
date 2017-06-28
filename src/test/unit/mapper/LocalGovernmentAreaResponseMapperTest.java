package mapper;

import com.elsynergy.nigerianpostcodes.mapper.LocalGovernmentAreaResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.response.LocalGovernmentAreaResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author silver.ibenye
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class LocalGovernmentAreaResponseMapperTest
{
    @InjectMocks
    private LocalGovernmentAreaResponseMapper localGovernmentAreaResponseMapper;

    @Test
    public void testMap()
    {
        final State state = new State();
        state.setCode("AB");
        state.setName("Abia");

        final LocalGovernmentArea in = new LocalGovernmentArea();
        in.setId(1);
        in.setName("testLga");
        in.setState(state);

        final LocalGovernmentAreaResponse out = this.localGovernmentAreaResponseMapper.map(in);

        assertEquals(in.getName(), out.getLocalGovernmentAreaName());
        assertEquals(in.getState().getCode(), out.getStateCode());
        assertEquals(in.getState().getName(), out.getStateName());
    }

}
