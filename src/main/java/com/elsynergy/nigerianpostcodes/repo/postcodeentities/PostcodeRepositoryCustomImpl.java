package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.LocalGovernmentArea;
import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.FacilityPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
public class PostcodeRepositoryCustomImpl implements PostcodeRepositoryCustom
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<FacilityPostcode> getFacilityPostcode(final String stateCode, final String localGovtAreaName, final String facilityName)
    {
        final String query = "SELECT " +
                            "s.id AS stateId" +
                            "s.name AS stateName, " +
                            "s.code AS stateCode, " +
                            "l.id AS lgaId, " +
                            "l.name AS lgaName, " +
                            "fp.id, " +
                            "fp.facility, " +
                            "fp.postcode " +
                        "FROM " +
                            "facility_postcodes fp " +
                        "INNER JOIN " +
                            "lgas l ON fp.lgaId = l.id " +
                        "INNER JOIN " +
                            "states s ON l.stateId = s.id " +
                        "WHERE " +
                            "s.code = ? " +
                        "AND " +
                            "l.name = COALESCE(?, l.name) " +
                        "AND " +
                            "fp.facility = COALESCE(?, fp.facility)";

        List<FacilityPostcode> ruralPostcodes = new ArrayList<>();

        ruralPostcodes = this.jdbcTemplate.query(
                query,
                new Object[] { stateCode, localGovtAreaName, facilityName }, (rs, rowNum) -> {
                    final FacilityPostcode facilityPostcode = new FacilityPostcode();
            final LocalGovernmentArea lga = new LocalGovernmentArea();
            final State state = new State();

            state.setId(rs.getInt("stateId"));
            state.setCode(rs.getString("stateCode"));
            state.setName(rs.getString("stateName"));

            lga.setId(rs.getInt("lgaId"));
            lga.setName(rs.getString("lgaName"));
            lga.setState(state);

            facilityPostcode.setId(rs.getInt("id"));
            facilityPostcode.setFacility(rs.getString("facility"));
            facilityPostcode.setPostcode(rs.getString("postcode"));
            facilityPostcode.setLocalGovernmentArea(lga);

            return facilityPostcode;
        });
        return null;
    }

    @Override
    public List<RuralPostcode> getRuralPostcode(final String stateCode, final String localGovtAreaName, final String district,
            final String town)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UrbanPostcode> getUrbanPostcode(final String stateCode, final String town, final String area, final String street)
    {
        // TODO Auto-generated method stub
        return null;
    }

}
