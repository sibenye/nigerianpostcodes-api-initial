package service;

import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Package;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Role;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;
import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;
import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;
import com.elsynergy.nigerianpostcodes.model.request.RegisterUserRequest;
import com.elsynergy.nigerianpostcodes.repo.userentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.UserRepository;
import com.elsynergy.nigerianpostcodes.service.userentities.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest
{
    @Mock
    private UserRepository userRepository;

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void test()
    {
        final RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setUsername("testUser");
        registerUserRequest.setPassword("1234");
        registerUserRequest.setPackageName(PackageEnum.BASIC);

        final Optional<Package> packageObj = Optional.of(new Package());
        final Optional<Role> roleObj = Optional.of(new Role());

        when(this.packageRepository.findOneByName(anyString())).thenReturn(packageObj);
        when(this.roleRepository.findOneByName(anyString())).thenReturn(roleObj);

        this.userService.registerUser(registerUserRequest);
        verify(this.packageRepository, times(1)).findOneByName(registerUserRequest.getPackageName().toString());
        verify(this.roleRepository, times(1)).findOneByName(RoleEnum.USER.toString());
        verify(this.userRepository, times(1)).save(any(User.class));
    }

}