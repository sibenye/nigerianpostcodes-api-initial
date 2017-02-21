package service;

import com.elsynergy.nigerianpostcodes.model.Response.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.repo.PostcodeSearchRepo;
import com.elsynergy.nigerianpostcodes.service.PostcodeSearchService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class PostcodeSearchServiceTest
{
    @InjectMocks
    private PostcodeSearchService postcodeSearchService;

    @Mock
    private PostcodeSearchRepo postcodeSearchRepo;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetRuralPostcodes() throws ResourceNotFoundException
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


        Mockito.doReturn(ruralPostcodes).when(this.postcodeSearchRepo).getRuralPostcodes(state, lga, town, district);

        this.postcodeSearchService.getRuralPostcodes(state, lga, town, district);

        Mockito.verify(this.postcodeSearchRepo).getRuralPostcodes(state, lga, town, district);
    }

    @Test
    public void testGetUrbanPostcodes() throws ResourceNotFoundException
    {
        final String state = "test_State";
        final String town = "test_town";
        final String street = "test_street";
        final String area = "test_area";

        final UrbanPostcode urbanPostcode = new UrbanPostcode();
        urbanPostcode.setState(state);
        urbanPostcode.setTown(town);
        urbanPostcode.setStreet(street);
        urbanPostcode.setArea(area);
        urbanPostcode.setPostcode(12345);

        final List<UrbanPostcode> urbanPostcodes = new ArrayList<>();
        urbanPostcodes.add(urbanPostcode);


        Mockito.doReturn(urbanPostcodes).when(this.postcodeSearchRepo).getUrbanPostcodes(state, town, street, area);

        this.postcodeSearchService.getUrbanPostcodes(state, town, street, area);

        Mockito.verify(this.postcodeSearchRepo).getUrbanPostcodes(state, town, street, area);
    }

    @Test
    public void testGetFacilityPostcodes() throws ResourceNotFoundException
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


        Mockito.doReturn(facilityPostcodes).when(this.postcodeSearchRepo).getFacilityPostcodes(state, lga, facility);

        this.postcodeSearchService.getFacilityPostcodes(state, lga, facility);

        Mockito.verify(this.postcodeSearchRepo).getFacilityPostcodes(state, lga, facility);
    }

}
