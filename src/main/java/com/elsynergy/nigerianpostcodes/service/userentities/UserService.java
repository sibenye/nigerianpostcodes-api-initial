package com.elsynergy.nigerianpostcodes.service.userentities;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;
import com.elsynergy.nigerianpostcodes.model.request.RegisterUserRequest;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.repo.userentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User Service Class.
 *
 * @author silver.ibenye
 *
 */
@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountResponseMapper accountResponseMapper;

    public AccountResponse registerUser(final RegisterUserRequest request) {
        final User user = new User(request);

        final Integer packageId = this.packageRepository.findOneByName(request.getPackageName().toString()).get().getId();
        user.setPackageId(packageId);

        final Integer roleId = this.roleRepository.findOneByName(request.getRole().toString()).get().getId();
        user.setRoleId(roleId);

        final User newuser =  this.userRepository.save(user);
        return this.accountResponseMapper.map(newuser);

    }

}
