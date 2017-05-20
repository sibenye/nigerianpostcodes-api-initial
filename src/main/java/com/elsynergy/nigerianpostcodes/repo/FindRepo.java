package com.elsynergy.nigerianpostcodes.repo;

import com.elsynergy.nigerianpostcodes.model.DAO.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
/**
 * Postcode Search Repo.
 *
 * @author silver.ibenye
 *
 */
public class FindRepo
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieve Rural postcodes.
     *
     * @param stateId
     * @param lgaId
     * @param town
     * @param district
     * @return List<RuralPostcode>
     */
    public List<RuralPostcode> getRuralPostcodes(final Integer stateId, final Integer lgaId, final String district, final String town)
    {
        final String query = "CALL get_rural_postcodes(" +
                "? " +
                ",? " +
                ",? " +
                ",?) ";

        List<RuralPostcode> ruralPostcodes = new ArrayList<>();

        ruralPostcodes = this.jdbcTemplate.query(
                query,
                new Object[]{stateId, lgaId, town, district},
                (rs, rowNum) -> {
                    final RuralPostcode ruralPostcode = new RuralPostcode();

                    ruralPostcode.setState(rs.getString("state"));
                    ruralPostcode.setLga(rs.getString("lga"));
                    ruralPostcode.setTown(rs.getString("town"));
                    ruralPostcode.setDistrict(rs.getString("district"));
                    ruralPostcode.setPostcode(rs.getInt("postcode"));

                    return ruralPostcode;
                }
            );

        return ruralPostcodes;
    }

    /**
     * Retrieve urban postcodes.
     *
     * @param stateId
     * @param town
     * @param street
     * @param area
     * @return List<UrbanPostcode>
     */
    public List<UrbanPostcode> getUrbanPostcodes(final Integer stateId, final String town, final String area, final String street)
    {
        final String query = "CALL get_urban_postcodes(" +
                "? " +
                ",? " +
                ",? " +
                ",?) ";

        List<UrbanPostcode> urbanPostcodes = new ArrayList<>();

        urbanPostcodes = this.jdbcTemplate.query(
                query,
                new Object[]{stateId, town, street, area},
                (rs, rowNum) -> {
                    final UrbanPostcode urbanPostcode = new UrbanPostcode();

                    urbanPostcode.setState(rs.getString("state"));
                    urbanPostcode.setTown(rs.getString("town"));
                    urbanPostcode.setStreet(rs.getString("street"));
                    urbanPostcode.setArea(rs.getString("area"));
                    urbanPostcode.setPostcode(rs.getInt("postcode"));

                    return urbanPostcode;
                }
            );

        return urbanPostcodes;
    }

    /**
     * Retrieve facility postcodes.
     *
     * @param stateId
     * @param lgaId
     * @param facility
     * @return List<FacilityPostcode>
     */
    public List<FacilityPostcode> getFacilityPostcodes(final Integer stateId, final Integer lgaId, final String facility)
    {
        final String query = "CALL get_facility_postcodes(" +
                "? " +
                ",? " +
                ",?) ";

        List<FacilityPostcode> facilityPostcodes = new ArrayList<>();

        facilityPostcodes = this.jdbcTemplate.query(
                query,
                new Object[]{stateId, lgaId, facility},
                (rs, rowNum) -> {
                    final FacilityPostcode facilityPostcode = new FacilityPostcode();

                    facilityPostcode.setState(rs.getString("state"));
                    facilityPostcode.setLga(rs.getString("lga"));
                    facilityPostcode.setFacility(rs.getString("facility"));
                    facilityPostcode.setPostcode(rs.getInt("postcode"));

                    return facilityPostcode;
                }
            );

        return facilityPostcodes;
    }

    /**
     * Retrieve States.
     *
     * @param stateId
     * @return List<State>
     */
    public List<State> getStates(final Integer stateId)
    {
        final String query = "CALL get_states( ? )";

        List<State> states = new ArrayList<>();

        states = this.jdbcTemplate.query(
                query,
                new Object[]{stateId},
                (rs, rowNum) -> {
                    final State state = new State();

                    state.setState(rs.getString("state"));
                    state.setStateId(rs.getInt("stateId"));
                    state.setStateCode(rs.getString("stateCode"));

                    return state;
                }
            );

        return states;
    }

    /**
     * Retrieve LGAs.
     *
     * @param stateId
     * @param lgaId
     * @return List<LGA>
     */
    public List<LGA> getLGAs(final Integer stateId, final Integer lgaId)
    {
        final String query = "CALL get_lgas( ?, ? )";

        List<LGA> lgas = new ArrayList<>();

        lgas = this.jdbcTemplate.query(
                query,
                new Object[]{stateId, lgaId},
                (rs, rowNum) -> {
                    final LGA lga = new LGA();

                    lga.setLgaId(rs.getInt("lgaId"));
                    lga.setLga(rs.getString("lga"));
                    lga.setState(rs.getString("state"));
                    lga.setStateId(rs.getInt("stateId"));
                    lga.setStateCode(rs.getString("stateCode"));

                    return lga;
                }
            );

        return lgas;
    }


}
