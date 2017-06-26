package service;

import com.elsynergy.nigerianpostcodes.mapper.FacilityPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.RuralPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.mapper.UrbanPostcodeResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.FacilityPostcodeRepository;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.RuralPostcodeRepository;
import com.elsynergy.nigerianpostcodes.repo.postcodeentities.UrbanPostcodeRepository;
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
    private FacilityPostcodeRepository facilityPostcodeRepository;

    @Mock
    private RuralPostcodeRepository ruralPostcodeRepository;

    @Mock
    private UrbanPostcodeRepository urbanPostcodeRepository;

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


    }

    @Test
    public void testGetFacilityPostcodesWithStateCodeAndLgaNameAndFacilityName() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = Arrays.asList(this.facilityPostcode);

        when(this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndFacility(
                anyString(), anyString(), anyString())).thenReturn(facilityPostcodes);

        this.postcodeService.getFacilityPostcodes("AB", "testLgs", "testFacility");

        verify(this.facilityPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndFacility(
                anyString(), anyString(), anyString());

        verify(this.facilityPostcodeResponseMapper, atLeastOnce()).map(any(FacilityPostcode.class));
    }

    @Test
    public void testGetFacilityPostcodesWithStateCodeAndLgaName() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = Arrays.asList(this.facilityPostcode);

        when(this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(
                anyString(), anyString())).thenReturn(facilityPostcodes);

        this.postcodeService.getFacilityPostcodes("AB", "testLgs", null);

        verify(this.facilityPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(
                anyString(), anyString());

        verify(this.facilityPostcodeResponseMapper, atLeastOnce()).map(any(FacilityPostcode.class));
    }

    @Test
    public void testGetFacilityPostcodesWithStateCode() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = Arrays.asList(this.facilityPostcode);

        when(this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCode(
                anyString())).thenReturn(facilityPostcodes);

        this.postcodeService.getFacilityPostcodes("AB", null, null);

        verify(this.facilityPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCode(
                anyString());

        verify(this.facilityPostcodeResponseMapper, atLeastOnce()).map(any(FacilityPostcode.class));
    }


    @Test(expected=ResourceNotFoundException.class)
    public void testGetFacilityPostcodesWillThrow() throws ResourceNotFoundException
    {
        final List<FacilityPostcode> facilityPostcodes = new ArrayList<>();

        when(this.facilityPostcodeRepository.findByLocalGovernmentAreaStateCode(
                anyString())).thenReturn(facilityPostcodes);

        this.postcodeService.getFacilityPostcodes("AB", null, null);

        verify(this.facilityPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCode(
                anyString());

        verify(this.facilityPostcodeResponseMapper, times(0)).map(any(FacilityPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCodeAndLgaNameAndDistrictAndTown() throws ResourceNotFoundException
    {
        final State state = new State();
        state.setCode("AB");
        state.setName("Abia");

        final LocalGovernmentArea localGovernmentArea = new LocalGovernmentArea();
        localGovernmentArea.setId(1);
        localGovernmentArea.setName("testLga");
        localGovernmentArea.setState(state);


        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrictAndTown(
                anyString(), anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes("AB", "testLgs", "testDistrict", "testTown");

        verify(this.ruralPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrictAndTown(
                anyString(), anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCodeAndLgaNameAndDistrict() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrict(
                anyString(), anyString(), anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes("AB", "testLgs", "testDistrict", null);

        verify(this.ruralPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaNameAndDistrict(
                anyString(), anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCodeAndLgaName() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(
                anyString(), anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes("AB", "testLgs", null, null);

        verify(this.ruralPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCodeAndLocalGovernmentAreaName(
                anyString(), anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetRuralPostcodesWithStateCode() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = Arrays.asList(this.ruralPostcode);

        when(this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCode(
                anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes("AB", null, null, null);

        verify(this.ruralPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCode(
                anyString());

        verify(this.ruralPostcodeResponseMapper, atLeastOnce()).map(any(RuralPostcode.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetRuralPostcodesWillThrow() throws ResourceNotFoundException
    {
        final List<RuralPostcode> ruralPostcodes = new ArrayList<>();

        when(this.ruralPostcodeRepository.findByLocalGovernmentAreaStateCode(
                anyString())).thenReturn(ruralPostcodes);

        this.postcodeService.getRuralPostcodes("AB", null, null, null);

        verify(this.ruralPostcodeRepository, times(1)).findByLocalGovernmentAreaStateCode(
                anyString());

        verify(this.ruralPostcodeResponseMapper, times(0)).map(any(RuralPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCodeAndTownAndAreaAndStreet() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.findByStateCodeAndTownAndAreaAndStreet(
                anyString(), anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes("AB", "testTown", "testArea", "testStreet");

        verify(this.urbanPostcodeRepository, times(1)).findByStateCodeAndTownAndAreaAndStreet(
                anyString(), anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCodeAndTownAndArea() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.findByStateCodeAndTownAndArea(
                anyString(), anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes("AB", "testTown", "testArea", null);

        verify(this.urbanPostcodeRepository, times(1)).findByStateCodeAndTownAndArea(
                anyString(), anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCodeAndTown() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.findByStateCodeAndTown(
                anyString(), anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes("AB", "testTown", null, null);

        verify(this.urbanPostcodeRepository, times(1)).findByStateCodeAndTown(
                anyString(), anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test
    public void testGetUrbanPostcodesWithStateCode() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = Arrays.asList(this.urbanPostcode);

        when(this.urbanPostcodeRepository.findByStateCode(
                anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes("AB", null, null, null);

        verify(this.urbanPostcodeRepository, times(1)).findByStateCode(
                anyString());

        verify(this.urbanPostcodeResponseMapper, atLeastOnce()).map(any(UrbanPostcode.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetUrbanPostcodesWillThrow() throws ResourceNotFoundException
    {
        final List<UrbanPostcode> urbanPostcodes = new ArrayList<>();

        when(this.urbanPostcodeRepository.findByStateCode(
                anyString()))
                .thenReturn(urbanPostcodes);

        this.postcodeService.getUrbanPostcodes("AB", null, null, null);

        verify(this.urbanPostcodeRepository, times(1)).findByStateCode(
                anyString());

        verify(this.urbanPostcodeResponseMapper, times(0)).map(any(UrbanPostcode.class));
    }

}
