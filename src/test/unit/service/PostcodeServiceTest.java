package service;

import com.elsynergy.nigerianpostcodes.mapper.FacilityPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.RuralPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.UrbanPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.model.request.FacilityPostcodeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RuralPostcodeRequest;
import com.elsynergy.nigerianpostcodes.model.request.UrbanPostcodeRequest;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.FacilityPostcodeRepositoryCustom;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.RuralPostcodeRepositoryCustom;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.UrbanPostcodeRepositoryCustom;
import com.elsynergy.nigerianpostcodes.service.postcodeentities.PostcodeService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostcodeServiceTest
{
    @Mock
    private FacilityPostcodeRepositoryCustom facilityPostcodeRepository;

    @Mock
    private RuralPostcodeRepositoryCustom ruralPostcodeRepository;

    @Mock
    private UrbanPostcodeRepositoryCustom urbanPostcodeRepository;

    @Mock
    private FacilityPostcodeResponseMapper facilityPostcodeResponseMapper;

    @Mock
    private RuralPostcodeResponseMapper ruralPostcodeResponseMapper;

    @Mock
    private UrbanPostcodeResponseMapper urbanPostcodeResponseMapper;

    @InjectMocks
    private PostcodeService postcodeService;

    private FacilityPostcode facilityPostcode;

    private RuralPostcode ruralPostcode;

    private UrbanPostcode urbanPostcode;

    private FacilityPostcodeRequest facilityPostcodeRequest;

    private RuralPostcodeRequest ruralPostcodeRequest;

    private UrbanPostcodeRequest urbanPostcodeRequest;

    @Before
    public void setup()
    {
        final State state = new State();
        state.setCode("AB");
        state.setName("Abia");

        final LocalGovernmentArea localGovernmentArea = new LocalGovernmentArea();
        localGovernmentArea.setId(1);
        localGovernmentArea.setName("testLga");
        localGovernmentArea.setState(state);

        this.facilityPostcode = new FacilityPostcode();
        this.facilityPostcode.setId(1);
        this.facilityPostcode.setFacility("testFacility");
        this.facilityPostcode.setPostcode("120983");
        this.facilityPostcode.setLocalGovernmentArea(localGovernmentArea);

        this.ruralPostcode = new RuralPostcode();
        this.ruralPostcode.setId(1);
        this.ruralPostcode.setDistrict("testDistrict");
        this.ruralPostcode.setTown("testTown");
        this.ruralPostcode.setPostcode("120983");
        this.ruralPostcode.setLocalGovernmentArea(localGovernmentArea);

        this.urbanPostcode = new UrbanPostcode();
        this.urbanPostcode.setId(1);
        this.urbanPostcode.setStreet("testStreet");
        this.urbanPostcode.setTown("testTown");
        this.urbanPostcode.setPostcode("120983");
        this.urbanPostcode.setArea("testArea");
        this.urbanPostcode.setState(state);

        this.facilityPostcodeRequest = new FacilityPostcodeRequest();
        this.facilityPostcodeRequest.setStateCode("AB");
        this.facilityPostcodeRequest.setLocalGovtAreaName("testLga");
        this.facilityPostcodeRequest.setFacilityName("testFacility");

        this.ruralPostcodeRequest = new RuralPostcodeRequest();
        this.ruralPostcodeRequest.setStateCode("AB");
        this.ruralPostcodeRequest.setLocalGovtAreaName("testLga");
        this.ruralPostcodeRequest.setDistrict("testDistrict");
        this.ruralPostcodeRequest.setTown("testTown");

        this.urbanPostcodeRequest = new UrbanPostcodeRequest();
        this.urbanPostcodeRequest.setStateCode("AB");
        this.urbanPostcodeRequest.setArea("testArea");
        this.urbanPostcodeRequest.setTown("testTown");
        this.urbanPostcodeRequest.setStreet("testStreet");
    }

    @Test
    public void testGetFacilityPostcodesWithStateCodeAndLgaNameAndFacilityName() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = Arrays.asList(this.facilityPostcode);

        when(this.facilityPostcodeRepository.getFacilityPostcodes(
                anyString(), anyString(), anyString())).thenReturn(facilityPostcodes);

        this.postcodeService.getFacilityPostcodes(this.facilityPostcodeRequest);

        verify(this.facilityPostcodeRepository, times(1)).getFacilityPostcodes(
                anyString(), anyString(), anyString());

        verify(this.facilityPostcodeResponseMapper, atLeastOnce()).map(any(FacilityPostcode.class));
    }

    @Test
    public void testGetFacilityPostcodesWithStateCodeAndLgaName() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = Arrays.asList(this.facilityPostcode);

        when(this.facilityPostcodeRepository.getFacilityPostcodes(
                anyString(), anyString(), anyString())).thenReturn(facilityPostcodes);

        this.facilityPostcodeRequest.setFacilityName(null);

        this.postcodeService.getFacilityPostcodes(this.facilityPostcodeRequest);

        verify(this.facilityPostcodeRepository, times(1)).getFacilityPostcodes(
                anyString(), anyString(), anyString());

        verify(this.facilityPostcodeResponseMapper, atLeastOnce()).map(any(FacilityPostcode.class));
    }

    @Test
    public void testGetFacilityPostcodesWithStateCode() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = Arrays.asList(this.facilityPostcode);

        when(this.facilityPostcodeRepository.getFacilityPostcodes(
                anyString(), anyString(), anyString())).thenReturn(facilityPostcodes);

        this.facilityPostcodeRequest.setFacilityName(null);
        this.facilityPostcodeRequest.setLocalGovtAreaName(null);

        this.postcodeService.getFacilityPostcodes(this.facilityPostcodeRequest);

        verify(this.facilityPostcodeRepository, times(1)).getFacilityPostcodes(
                anyString(), anyString(), anyString());

        verify(this.facilityPostcodeResponseMapper, atLeastOnce()).map(any(FacilityPostcode.class));
    }


    @Test(expected=ResourceNotFoundException.class)
    public void testGetFacilityPostcodesWillThrow() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = new ArrayList<>();

        when(this.facilityPostcodeRepository.getFacilityPostcodes(
                anyString(), anyString(), anyString())).thenReturn(facilityPostcodes);

        this.postcodeService.getFacilityPostcodes(this.facilityPostcodeRequest);

        verify(this.facilityPostcodeRepository, times(1)).getFacilityPostcodes(
                anyString(), anyString(), anyString());

        verify(this.facilityPostcodeResponseMapper, times(0)).map(any(FacilityPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCodeAndLgaNameAndDistrictAndTown() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes(this.ruralPostcodeRequest);

        verify(this.ruralPostcodeRepository, times(1)).getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCodeAndLgaNameAndDistrict() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.ruralPostcodeRequest.setTown(null);

        this.postcodeService.getRuralPostcodes(this.ruralPostcodeRequest);

        verify(this.ruralPostcodeRepository, times(1)).getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCodeAndLgaName() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.ruralPostcodeRequest.setTown(null);
        this.ruralPostcodeRequest.setDistrict(null);

        this.postcodeService.getRuralPostcodes(this.ruralPostcodeRequest);

        verify(this.ruralPostcodeRepository, times(1)).getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCode() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.ruralPostcodeRequest.setTown(null);
        this.ruralPostcodeRequest.setDistrict(null);
        this.ruralPostcodeRequest.setLocalGovtAreaName(null);

        this.postcodeService.getRuralPostcodes(this.ruralPostcodeRequest);

        verify(this.ruralPostcodeRepository, times(1)).getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetRuralPostcodesWillThrow() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = new ArrayList<>();

        when(this.ruralPostcodeRepository.getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes(this.ruralPostcodeRequest);

        verify(this.ruralPostcodeRepository, times(1)).getRuralPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, times(0)).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCodeAndTownAndAreaAndStreet() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes(this.urbanPostcodeRequest);

        verify(this.urbanPostcodeRepository, times(1)).getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCodeAndTownAndArea() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.urbanPostcodeRequest.setStreet(null);

        this.postcodeService.getUrbanPostcodes(this.urbanPostcodeRequest);

        verify(this.urbanPostcodeRepository, times(1)).getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCodeAndTown() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.urbanPostcodeRequest.setStreet(null);
        this.urbanPostcodeRequest.setArea(null);

        this.postcodeService.getUrbanPostcodes(this.urbanPostcodeRequest);

        verify(this.urbanPostcodeRepository, times(1)).getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCode() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.urbanPostcodeRequest.setStreet(null);
        this.urbanPostcodeRequest.setArea(null);
        this.urbanPostcodeRequest.setTown(null);

        this.postcodeService.getUrbanPostcodes(this.urbanPostcodeRequest);

        verify(this.urbanPostcodeRepository, times(1)).getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetUrbanPostcodesWillThrow() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = new ArrayList<>();

        when(this.urbanPostcodeRepository.getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes(this.urbanPostcodeRequest);

        verify(this.urbanPostcodeRepository, times(1)).getUrbanPostcodes(
                anyString(), anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, times(0)).map(any(UrbanPostcode.class));
    }

}
