package com.elsynergy.nigerianpostcodes.repo;

import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;
import com.elsynergy.nigerianpostcodes.model.Response.UrbanPostcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostcodeSearchRepo
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieve Rural postcodes.
     *
     * @param state
     * @param lga
     * @param town
     * @param district
     * @return List<RuralPostcode>
     */
    public List<RuralPostcode> getRuralPostcodes(final String state, final String lga, final String town, final String district)
    {
        final String query = "CALL get_rural_postcodes(" +
                "? " +
                ",? " +
                ",? " +
                ",?) ";

        List<RuralPostcode> ruralPostcodes = new ArrayList<>();

        ruralPostcodes = this.jdbcTemplate.query(
                query,
                new Object[]{state, lga, town, district},
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
     * @param state
     * @param town
     * @param street
     * @param area
     * @return List<UrbanPostcode>
     */
    public List<UrbanPostcode> getUrbanPostcodes(final String state, final String town, final String street, final String area)
    {
        final String query = "CALL get_urban_postcodes(" +
                "? " +
                ",? " +
                ",? " +
                ",?) ";

        List<UrbanPostcode> ruralPostcodes = new ArrayList<>();

        ruralPostcodes = this.jdbcTemplate.query(
                query,
                new Object[]{state, town, street, area},
                (rs, rowNum) -> {
                    final UrbanPostcode ruralPostcode = new UrbanPostcode();

                    ruralPostcode.setState(rs.getString("state"));
                    ruralPostcode.setTown(rs.getString("town"));
                    ruralPostcode.setStreet(rs.getString("street"));
                    ruralPostcode.setArea(rs.getString("area"));
                    ruralPostcode.setPostcode(rs.getInt("postcode"));

                    return ruralPostcode;
                }
            );

        return ruralPostcodes;
    }


}
