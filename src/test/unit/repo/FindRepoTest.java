package repo;

import com.elsynergy.nigerianpostcodes.model.DAO.*;
import com.elsynergy.nigerianpostcodes.repo.FindRepo;

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

public class FindRepoTest
{
    @InjectMocks
    private FindRepo findRepo;

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
        final Integer stateId = 5;
        final Integer lgaId = 78;
        final String town = "test_town";
        final String district = "test_district";

        final RuralPostcode ruralPostcode = new RuralPostcode();
        ruralPostcode.setState("test-state");
        ruralPostcode.setLga("test-lga");
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

        this.findRepo.getRuralPostcodes(stateId, lgaId, district, town);
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
        final Integer stateId = 20;
        final String street = "test_street";
        final String town = "test_town";
        final String area = "test_area";

        final UrbanPostcode urbanPostcode = new UrbanPostcode();
        urbanPostcode.setState("test-state");
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

        this.findRepo.getUrbanPostcodes(stateId, street, area, town);
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
        final Integer stateId = 20;
        final Integer lgaId = 60;
        final String facility = "test_facility";

        final FacilityPostcode facilityPostcode = new FacilityPostcode();
        facilityPostcode.setState("test-state");
        facilityPostcode.setLga("test-lga");
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

        this.findRepo.getFacilityPostcodes(stateId, lgaId, facility);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetStates()
    {
        final Integer stateId = 20;

        final State state = new State();
        state.setState("test-state");
        state.setStateId(20);
        state.setStateCode("test-statecode");

        final List<State> states = new ArrayList<>();
        states.add(state);

        Mockito.doReturn(states).when(this.jdbcTemplate)
        .query(
            Mockito.anyString(),
            Mockito.any(Object[].class),
            Mockito.any(RowMapper.class)
        );

        this.findRepo.getStates(stateId);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testGetLGAs()
    {
        final Integer stateId = 20;
        final Integer lgaId = 77;

        final LGA lga = new LGA();
        lga.setLgaId(lgaId);
        lga.setLga("test-lga");
        lga.setState("test-state");
        lga.setStateId(20);
        lga.setStateCode("test-statecode");

        final List<LGA> lgas = new ArrayList<>();
        lgas.add(lga);

        Mockito.doReturn(lgas).when(this.jdbcTemplate)
        .query(
            Mockito.anyString(),
            Mockito.any(Object[].class),
            Mockito.any(RowMapper.class)
        );

        this.findRepo.getLGAs(stateId, lgaId);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }

}
