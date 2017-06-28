package com.elsynergy.nigerianpostcodes.repo.postcodeentities;

import com.elsynergy.nigerianpostcodes.model.DAO.geograpghyentities.State;
import com.elsynergy.nigerianpostcodes.model.DAO.postcodeentities.UrbanPostcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author silver.ibenye
 *
 */
@Repository
public class UrbanPostcodeRepositoryCustomImpl implements UrbanPostcodeRepositoryCustom
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UrbanPostcode> getUrbanPostcodes(final String stateCode, final String town, final String area, final String street)
    {
        final String query = "SELECT " +
                            "s.id AS stateId, " +
                            "s.name AS stateName, " +
                            "s.code AS stateCode, " +
                            "up.id, " +
                            "up.town, " +
                            "up.street, " +
                            "up.area, " +
                            "up.postcode " +
                        "FROM " +
                            "urban_postcodes up " +
                        "INNER JOIN " +
                            "states s ON up.stateId = s.id " +
                        "WHERE " +
                            "s.code = ? " +
                        "AND " +
                            "up.town = COALESCE(?, up.town) " +
                        "AND " +
                            "up.area = COALESCE(?, up.area) " +
                        "AND " +
                            "up.street = COALESCE(?, up.street)";

        List<UrbanPostcode> urbanPostcodes = new ArrayList<>();

        urbanPostcodes = this.jdbcTemplate.query(
                query,
                new Object[] { stateCode, town, area, street }, (rs, rowNum) -> {
                    final UrbanPostcode urbanPostcode = new UrbanPostcode();
            final State state = new State();

            state.setId(rs.getInt("stateId"));
            state.setCode(rs.getString("stateCode"));
            state.setName(rs.getString("stateName"));


            urbanPostcode.setId(rs.getInt("id"));
            urbanPostcode.setTown(rs.getString("town"));
            urbanPostcode.setArea(rs.getString("area"));
            urbanPostcode.setStreet(rs.getString("street"));
            urbanPostcode.setPostcode(rs.getString("postcode"));
            urbanPostcode.setState(state);

            return urbanPostcode;
        });

        return urbanPostcodes;
    }

}
