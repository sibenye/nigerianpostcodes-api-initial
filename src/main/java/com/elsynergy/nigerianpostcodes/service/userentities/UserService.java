package com.elsynergy.nigerianpostcodes.service.userentities;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;
import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;
import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;
import com.elsynergy.nigerianpostcodes.model.request.RegisterUserRequest;
import com.elsynergy.nigerianpostcodes.repo.userentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public User registerUser(final RegisterUserRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());;
        user.setPasswordHash(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setActive(true);

        final Integer packageId = PackageEnum.ID_LOOKUP_MAP.get(request.getPackageName().toString());
        user.setPackageId(packageId);

        final Integer roleId = RoleEnum.ID_LOOKUP_MAP.get(RoleEnum.USER.toString());
        user.setRoleId(roleId);

        //final Date nowDate = new Date();
        //user.setDateCreated(nowDate);
        //user.setDateModified(nowDate);

        //this.userRepository.save(user);

        return this.userRepository.save(user);
    }

}
