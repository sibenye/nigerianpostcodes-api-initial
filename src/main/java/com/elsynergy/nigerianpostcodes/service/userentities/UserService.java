package com.elsynergy.nigerianpostcodes.service.userentities;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Package;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Role;
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
    private final UserRepository userRepository;

    @Autowired
    private final PackageRepository packageRepository;

    @Autowired
    private final RoleRepository roleRepository;

    public UserService(final UserRepository userRepository, final PackageRepository packageRepository, final RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.packageRepository = packageRepository;
        this.roleRepository = roleRepository;
    }

    public User registerUser(final RegisterUserRequest request) {
        final User user = new User();
        user.setUsername(request.getUsername());;
        user.setPasswordHash(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setActive(true);

        final Integer packageId = PackageEnum.ID_LOOKUP_MAP.get(request.getPackageName().toString());
        final Package userPackage = this.packageRepository.findOne(packageId);
        user.setUserPackage(userPackage);

        final Integer roleId = RoleEnum.ID_LOOKUP_MAP.get(RoleEnum.USER.toString());
        final Role userRole = this.roleRepository.findOne(roleId);
        user.setUserRole(userRole);

        this.userRepository.save(user);

        return null;
    }

}
