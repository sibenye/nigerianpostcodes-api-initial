package mapper;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Feature;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.PackageType;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.Role;
import com.elsynergy.nigerianpostcodes.model.DAO.userentities.User;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.repo.userentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.userentities.RoleRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountResponseMapperTest
{
    @Mock
    private PackageRepository packageRepository;

    @Mock
    private RoleRepository roleRespository;

    @InjectMocks
    private AccountResponseMapper accountResponseMapper;

    @Test
    public void test()
    {
        final Feature feature = new Feature();
        feature.setName("testFeature");

        final Set<Feature> features = new HashSet<>();
        features.add(feature);

        final PackageType packageType = new PackageType();
        packageType.setId(2);
        packageType.setName("testPackageType");
        packageType.setFeatureSet(features);

        final Role role = new Role();
        role.setId(3);
        role.setName("testRole");

        final User user = new User();
        user.setId((long) 100);
        user.setUsername("testUserName");
        user.setActive(true);
        user.setPackageId(packageType.getId());
        user.setRoleId(role.getId());

        final AccountResponse expectedAcctResponse = new AccountResponse();
        expectedAcctResponse.setUsername(user.getUsername());
        expectedAcctResponse.setActive(user.getActive());
        expectedAcctResponse.setPackageType(packageType.getName());
        expectedAcctResponse.setRole(role.getName());
        expectedAcctResponse.setFeatures(Arrays.asList(feature.getName()));

        when(this.packageRepository.findOne(packageType.getId())).thenReturn(packageType);
        when(this.roleRespository.findOne(role.getId())).thenReturn(role);

        final AccountResponse actualAcctResponse = this.accountResponseMapper.map(user);

        assertEquals(expectedAcctResponse.getUsername(), actualAcctResponse.getUsername());
        assertEquals(expectedAcctResponse.getActive(), actualAcctResponse.getActive());
        assertEquals(expectedAcctResponse.getPackageType(), actualAcctResponse.getPackageType());
        assertEquals(expectedAcctResponse.getRole(), actualAcctResponse.getRole());
        assertEquals(expectedAcctResponse.getFeatures().get(0), actualAcctResponse.getFeatures().get(0));

        verify(this.packageRepository, times(1)).findOne(packageType.getId());
        verify(this.roleRespository, times(1)).findOne(role.getId());
    }

}
