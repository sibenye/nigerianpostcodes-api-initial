package mapper;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.*;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse.SubscriptionDetails;
import com.elsynergy.nigerianpostcodes.repo.accountentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.SubscriptionRepository;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountResponseMapperTest
{
    @Mock
    private PackageRepository packageRepository;

    @Mock
    private RoleRepository roleRespository;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private AccountResponseMapper accountResponseMapper;

    private DateTimeService dateTimeService;

    @Mock
    private DateTimeService dateTimeServiceMock;

    private Account account;

    private Role role;

    private PackageType packageType;

    private Privilege privilege;

    @Before
    public void setup()
    {
        this.dateTimeService = new DateTimeService();
        this.getAccountObject();

    }

    @Test
    public void test()
    {

        final Subscription subscription = new Subscription();
        subscription.setAccount(this.account);
        subscription.setDurationInMonths(3);
        subscription.setNumberOfRequestsAllowed(300);

        final Calendar dateNow = this.dateTimeService.getCurrentDateAndTime();
        final Date startDate = dateNow.getTime();
        final String startDateString = this.dateTimeService.getStringFormattedDate(startDate);
        subscription.setStartDate(startDate);

        dateNow.add(Calendar.MONTH, 3);
        final Date endDate = dateNow.getTime();
        final String endDateString = this.dateTimeService.getStringFormattedDate(endDate);
        subscription.setEndDate(endDate);

        final AccountResponse expectedAcctResponse = new AccountResponse();
        expectedAcctResponse.setUsername(this.account.getName());
        expectedAcctResponse.setActive(this.account.getActive());
        expectedAcctResponse.setPackageType(this.packageType.getName());
        expectedAcctResponse.setRole(this.role.getName());
        expectedAcctResponse.setPrivileges(Arrays.asList(this.privilege.getName()));
        expectedAcctResponse.setAccountKey(this.account.getAccountKey());

        final SubscriptionDetails expectedSubscriptionDetails = expectedAcctResponse.new SubscriptionDetails();
        expectedSubscriptionDetails.setDurationInMonths(3);
        expectedSubscriptionDetails.setNumberOfRequestsAllowed(300);
        expectedSubscriptionDetails.setStartDate(startDateString);
        expectedSubscriptionDetails.setEndDate(endDateString);
        expectedAcctResponse.setSubscriptionDetails(expectedSubscriptionDetails);

        when(this.subscriptionRepository.findOneByAccountId(this.account.getId())).thenReturn(Optional.of(subscription));
        when(this.dateTimeServiceMock.getCurrentDateAndTime()).thenReturn(dateNow);
        when(this.dateTimeServiceMock.getStringFormattedDate(startDate)).thenReturn(startDateString);
        when(this.dateTimeServiceMock.getStringFormattedDate(endDate)).thenReturn(endDateString);

        final AccountResponse actualAcctResponse = this.accountResponseMapper.map(this.account);

        assertEquals(expectedAcctResponse.getAccountName(), actualAcctResponse.getAccountName());
        assertEquals(expectedAcctResponse.getAccountKey(), actualAcctResponse.getAccountKey());
        assertEquals(expectedAcctResponse.getActive(), actualAcctResponse.getActive());
        assertEquals(expectedAcctResponse.getPackageType(), actualAcctResponse.getPackageType());
        assertEquals(expectedAcctResponse.getRole(), actualAcctResponse.getRole());
        assertEquals(expectedAcctResponse.getPrivileges().get(0),
                actualAcctResponse.getPrivileges().get(0));
        assertEquals(expectedAcctResponse.getSubscriptionDetails().getNumberOfRequestsAllowed(),
                actualAcctResponse.getSubscriptionDetails().getNumberOfRequestsAllowed());
        assertEquals(expectedAcctResponse.getSubscriptionDetails().getDurationInMonths(),
                actualAcctResponse.getSubscriptionDetails().getDurationInMonths());
        assertEquals(expectedAcctResponse.getSubscriptionDetails().getStartDate(),
                actualAcctResponse.getSubscriptionDetails().getStartDate());
        assertEquals(expectedAcctResponse.getSubscriptionDetails().getEndDate(),
                actualAcctResponse.getSubscriptionDetails().getEndDate());

    }

    @Test
    public void testWithNullSubscription()
    {
        final AccountResponse expectedAcctResponse = new AccountResponse();
        expectedAcctResponse.setUsername(this.account.getName());
        expectedAcctResponse.setActive(this.account.getActive());
        expectedAcctResponse.setPackageType(this.packageType.getName());
        expectedAcctResponse.setRole(this.role.getName());
        expectedAcctResponse.setPrivileges(Arrays.asList(this.privilege.getName()));
        expectedAcctResponse.setAccountKey(this.account.getAccountKey());

        when(this.subscriptionRepository.findOneByAccountId(this.account.getId())).thenReturn(Optional.empty());

        final AccountResponse actualAcctResponse = this.accountResponseMapper.map(this.account);

        assertEquals(expectedAcctResponse.getAccountName(), actualAcctResponse.getAccountName());
        assertEquals(expectedAcctResponse.getAccountKey(), actualAcctResponse.getAccountKey());
        assertEquals(expectedAcctResponse.getActive(), actualAcctResponse.getActive());
        assertEquals(expectedAcctResponse.getPackageType(), actualAcctResponse.getPackageType());
        assertEquals(expectedAcctResponse.getRole(), actualAcctResponse.getRole());
        assertEquals(expectedAcctResponse.getPrivileges().get(0), actualAcctResponse.getPrivileges().get(0));
        assertEquals(expectedAcctResponse.getSubscriptionDetails(), actualAcctResponse.getSubscriptionDetails());

    }


    private void getAccountObject()
    {
        final Privilege privilege = new Privilege();
        privilege.setName("testFeature");
        this.privilege = privilege;

        final Set<Privilege> privileges = new HashSet<>();
        privileges.add(this.privilege);

        final PackageType packageType = new PackageType();
        packageType.setId(2);
        packageType.setName("testPackageType");
        packageType.setPrivilegeSet(privileges);
        this.packageType = packageType;

        final Role role = new Role();
        role.setId(3);
        role.setName("testRole");
        this.role = role;

        final Account account = new Account();
        account.setId((long) 100);
        account.setName("testUserName");
        account.setActive(true);
        account.setPackageType(this.packageType);
        account.setRole(this.role);
        account.setAccountKey("3ed5tHgn");

        this.account = account;

    }

}
