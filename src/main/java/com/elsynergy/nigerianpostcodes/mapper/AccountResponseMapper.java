package com.elsynergy.nigerianpostcodes.mapper;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Feature;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.PackageType;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.repo.userentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountResponseMapper implements IResponseMapper<User, AccountResponse>
{
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private RoleRepository roleRespository;

    @Override
    public AccountResponse map(final User toMap)
    {
        final AccountResponse accountResponse = new AccountResponse();
        accountResponse.setUsername(toMap.getUsername());
        accountResponse.setActive(toMap.getActive());

        final PackageType packageType = this.packageRepository.findOne(toMap.getPackageId());
        final String packageName = packageType.getName();
        accountResponse.setPackageType(packageName);

        final String role = this.roleRespository.findOne(toMap.getRoleId()).getName();
        accountResponse.setRole(role);

        final List<String> featureList = new ArrayList<>();

        for (final Feature feature : packageType.getFeatureSet()) {
            featureList.add(feature.getName());
        }

        accountResponse.setFeatures(featureList);

        return accountResponse;
    }

}
