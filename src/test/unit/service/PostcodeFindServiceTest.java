package service;

import com.elsynergy.nigerianpostcodes.model.DAO.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.repo.FindRepo;
import com.elsynergy.nigerianpostcodes.service.FindService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class PostcodeFindServiceTest
{
    @InjectMocks
    private FindService postcodeSearchService;

    @Mock
    private FindRepo postcodeFindRepo;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRuralPostcodes() throws ResourceNotFoundException
    {
        final Integer stateId = 13;
        final Integer lgaId = 100;
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


        Mockito.doReturn(ruralPostcodes).when(this.postcodeFindRepo).getRuralPostcodes(stateId, lgaId, district, town);

        this.postcodeSearchService.getRuralPostcodes(stateId, lgaId, town, district);

        Mockito.verify(this.postcodeFindRepo).getRuralPostcodes(stateId, lgaId, town, district);
    }

    @Test
    public void testGetUrbanPostcodes() throws ResourceNotFoundException
    {
        final Integer stateId = 20;
        final String town = "test_town";
        final String street = "test_street";
        final String area = "test_area";

        final UrbanPostcode urbanPostcode = new UrbanPostcode();
        urbanPostcode.setState("test-state");
        urbanPostcode.setTown(town);
        urbanPostcode.setStreet(street);
        urbanPostcode.setArea(area);
        urbanPostcode.setPostcode(12345);

        final List<UrbanPostcode> urbanPostcodes = new ArrayList<>();
        urbanPostcodes.add(urbanPostcode);


        Mockito.doReturn(urbanPostcodes).when(this.postcodeFindRepo).getUrbanPostcodes(stateId, town, area, street);

        this.postcodeSearchService.getUrbanPostcodes(stateId, town, street, area);

        Mockito.verify(this.postcodeFindRepo).getUrbanPostcodes(stateId, town, street, area);
    }

    @Test
    public void testGetFacilityPostcodes() throws ResourceNotFoundException
    {
        final Integer stateId = 20;
        final Integer lgaId = 100;
        final String facility = "test_facility";

        final FacilityPostcode facilityPostcode = new FacilityPostcode();
        facilityPostcode.setState("test-state");
        facilityPostcode.setLga("test-lga");
        facilityPostcode.setFacility(facility);
        facilityPostcode.setPostcode(12345);

        final List<FacilityPostcode> facilityPostcodes = new ArrayList<>();
        facilityPostcodes.add(facilityPostcode);


        Mockito.doReturn(facilityPostcodes).when(this.postcodeFindRepo).getFacilityPostcodes(stateId, lgaId, facility);

        this.postcodeSearchService.getFacilityPostcodes(stateId, lgaId, facility);

        Mockito.verify(this.postcodeFindRepo).getFacilityPostcodes(stateId, lgaId, facility);
    }

}
