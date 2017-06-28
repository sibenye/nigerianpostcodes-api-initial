package mapper;

import com.elsynergy.nigerianpostcodes.mapper.FacilityPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.response.FacilityPostcodeResponse;

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
public class FacilityPostcodeResponseMapperTest
{
    @InjectMocks
    private FacilityPostcodeResponseMapper facilityPostcodeResponseMapper;

    @Test
    public void testMap()
    {
        final State state = new State();
        state.setCode("AB");
        state.setName("Abia");

        final LocalGovernmentArea localGovernmentArea = new LocalGovernmentArea();
        localGovernmentArea.setId(1);
        localGovernmentArea.setName("testLga");
        localGovernmentArea.setState(state);

        final FacilityPostcode in = new FacilityPostcode();
        in.setFacility("testFacility");
        in.setPostcode("345678");
        in.setLocalGovernmentArea(localGovernmentArea);

        final FacilityPostcodeResponse out = this.facilityPostcodeResponseMapper.map(in);

        assertEquals(in.getFacility(), out.getFacilityName());
        assertEquals(in.getPostcode(), out.getPostcode());
        assertEquals(in.getLocalGovernmentArea().getName(), out.getLocalGovernmentAreaName());
        assertEquals(in.getLocalGovernmentArea().getState().getCode(), out.getStateCode());
        assertEquals(in.getLocalGovernmentArea().getState().getName(), out.getStateName());
    }

}
