package repo;

import com.elsynergy.nigerianpostcodes.model.Response.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.repo.PostcodeSearchRepo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class PostcodeSearchRepoTest
{
    @InjectMocks
    private PostcodeSearchRepo postcodeSearchRepo;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetRuralPostcodes()
    {
        final String state = "test_State";
        final String lga = "test_lga";
        final String town = "test_town";
        final String district = "test_district";

        final RuralPostcode ruralPostcode = new RuralPostcode();
        ruralPostcode.setState(state);
        ruralPostcode.setLga(lga);
        ruralPostcode.setTown(town);
        ruralPostcode.setDistrict(district);
        ruralPostcode.setPostcode(12345);

        final List<RuralPostcode> ruralPostcodes = new ArrayList<>();
        ruralPostcodes.add(ruralPostcode);

        Mockito.doReturn(ruralPostcodes).when(this.jdbcTemplate)
        .query(
            Mockito.anyString(),
            Mockito.any(Object[].class),
            Mockito.any(RowMapper.class)
        );

        this.postcodeSearchRepo.getRuralPostcodes(state, lga, town, district);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetUrbanPostcodes()
    {
        final String state = "test_State";
        final String street = "test_street";
        final String town = "test_town";
        final String area = "test_area";

        final UrbanPostcode urbanPostcode = new UrbanPostcode();
        urbanPostcode.setState(state);
        urbanPostcode.setStreet(street);
        urbanPostcode.setTown(town);
        urbanPostcode.setArea(area);
        urbanPostcode.setPostcode(12345);

        final List<UrbanPostcode> urbanPostcodes = new ArrayList<>();
        urbanPostcodes.add(urbanPostcode);

        Mockito.doReturn(urbanPostcodes).when(this.jdbcTemplate)
        .query(
            Mockito.anyString(),
            Mockito.any(Object[].class),
            Mockito.any(RowMapper.class)
        );

        this.postcodeSearchRepo.getRuralPostcodes(state, street, town, area);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }


    @SuppressWarnings("unchecked")
    @Test
    public void testGetFacilityPostcodes()
    {
        final String state = "test_State";
        final String lga = "test_lga";
        final String facility = "test_facility";

        final FacilityPostcode facilityPostcode = new FacilityPostcode();
        facilityPostcode.setState(state);
        facilityPostcode.setLga(lga);
        facilityPostcode.setFacility(facility);
        facilityPostcode.setPostcode(12345);

        final List<FacilityPostcode> facilityPostcodes = new ArrayList<>();
        facilityPostcodes.add(facilityPostcode);

        Mockito.doReturn(facilityPostcodes).when(this.jdbcTemplate)
        .query(
            Mockito.anyString(),
            Mockito.any(Object[].class),
            Mockito.any(RowMapper.class)
        );

        this.postcodeSearchRepo.getFacilityPostcodes(state, lga, facility);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }

}
