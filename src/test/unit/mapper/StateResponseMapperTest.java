package mapper;

import com.elsynergy.nigerianpostcodes.mapper.StateResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.response.StateResponse;

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
public class StateResponseMapperTest
{
    @InjectMocks
    private StateResponseMapper stateResponseMapper;

    @Test
    public void testMap()
    {
        final State in = new State();
        in.setCode("AB");
        in.setId(1);
        in.setName("Abia");

        final StateResponse out = this.stateResponseMapper.map(in);

        assertEquals(in.getCode(), out.getStateCode());
        assertEquals(in.getName(), out.getStateName());
    }

}
