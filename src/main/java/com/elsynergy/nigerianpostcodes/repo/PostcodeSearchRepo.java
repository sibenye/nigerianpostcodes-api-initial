package com.elsynergy.nigerianpostcodes.repo;

import com.elsynergy.nigerianpostcodes.model.Response.RuralPostcode;

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

    public List<RuralPostcode> GetRuralPostcodes(final String state, final String lga, final String town, final String district)
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

}
