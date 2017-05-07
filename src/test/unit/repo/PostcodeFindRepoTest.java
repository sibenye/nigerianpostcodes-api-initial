package repo;

import com.elsynergy.nigerianpostcodes.model.DAO.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.UrbanPostcode;
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

public class PostcodeFindRepoTest
{
    @InjectMocks
    private FindRepo postcodeFindRepo;

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

        this.postcodeFindRepo.getRuralPostcodes(stateId, lgaId, district, town);
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

        this.postcodeFindRepo.getUrbanPostcodes(stateId, street, area, town);
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

        this.postcodeFindRepo.getFacilityPostcodes(stateId, lgaId, facility);
        Mockito.verify(this.jdbcTemplate)
            .query(
                    Mockito.anyString(),
                    Mockito.any(Object[].class),
                    Mockito.any(RowMapper.class)
            );
    }

}
