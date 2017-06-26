package mapper;

import com.elsynergy.nigerianpostcodes.mapper.RuralPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.response.RuralPostcodeResponse;

import org.junit.Test;
import org.mockito.InjectMocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class RuralPostcodeResponseMapperTest
{
    @InjectMocks
    private RuralPostcodeResponseMapper ruralPostcodeResponseMapper;

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

        final RuralPostcode in = new RuralPostcode();
        in.setDistrict("testDistrict");
        in.setPostcode("23444");
        in.setTown("testTown");
        in.setLocalGovernmentArea(localGovernmentArea);

        final RuralPostcodeResponse out = this.ruralPostcodeResponseMapper.map(in);

        assertEquals(in.getDistrict(), out.getDistrict());
        assertEquals(in.getPostcode(), out.getPostcode());
        assertEquals(in.getTown(), out.getTown());
        assertEquals(in.getLocalGovernmentArea().getName(), out.getLocalGovernmentAreaName());
        assertEquals(in.getLocalGovernmentArea().getState().getCode(), out.getStateCode());
        assertEquals(in.getLocalGovernmentArea().getState().getName(), out.getStateName());
    }

}
