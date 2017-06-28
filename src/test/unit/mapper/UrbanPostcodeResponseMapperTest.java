package mapper;

import com.elsynergy.nigerianpostcodes.mapper.UrbanPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.model.response.UrbanPostcodeResponse;

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
public class UrbanPostcodeResponseMapperTest
{
    @InjectMocks
    private UrbanPostcodeResponseMapper urbanPostcodeResponseMapper;

    @Test
    public void testMap()
    {
        final State state = new State();
        state.setCode("AB");
        state.setName("Abia");

        final UrbanPostcode in = new UrbanPostcode();
        in.setArea("testArea");
        in.setPostcode("34677");
        in.setStreet("testStreet");
        in.setTown("testTown");
        in.setState(state);

        final UrbanPostcodeResponse out = this.urbanPostcodeResponseMapper.map(in);

        assertEquals(in.getArea(), out.getArea());
        assertEquals(in.getPostcode(), out.getPostcode());
        assertEquals(in.getStreet(), out.getStreet());
        assertEquals(in.getTown(), out.getTown());
        assertEquals(in.getState().getCode(), out.getStateCode());
        assertEquals(in.getState().getName(), out.getStateName());
    }

}
