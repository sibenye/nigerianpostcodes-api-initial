package mapper;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.*;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse;
import com.elsynergy.nigerianpostcodes.model.response.AccountResponse.SubscriptionDetails;
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
    private DateTimeService dateTimeServiceMock;

    @InjectMocks
    private AccountResponseMapper accountResponseMapper;

    private DateTimeService dateTimeService;

    private Account account;

    private Role role;

    private PackageType packageType;

    private Privilege privilege;

    private AccountSubscription subscription;

    private Calendar dateNow;

    @Before
    public void setup()
    {
        this.dateTimeService = new DateTimeService();
        this.dateNow = this.dateTimeService.getCurrentDateAndTime();
        this.getAccountObject();

    }

    @Test
    public void test()
    {
        final String startDateString = this.dateTimeService.getStringFormattedDate(this.subscription.getStartDate());

        final String endDateString = this.dateTimeService.getStringFormattedDate(this.subscription.getEndDate());

        final AccountResponse expectedAcctResponse = new AccountResponse();
        expectedAcctResponse.setUsername(this.account.getName());
        expectedAcctResponse.setActive(this.account.getActive());
        expectedAcctResponse.setPackageType(this.packageType.getName());
        expectedAcctResponse.setRole(this.role.getName());
        expectedAcctResponse.setPrivileges(Arrays.asList(this.privilege.getName()));
        expectedAcctResponse.setAccountKey(this.account.getAccountKey());

        final SubscriptionDetails expectedSubscriptionDetails = expectedAcctResponse.new SubscriptionDetails();
        expectedSubscriptionDetails.setDurationInMonths(this.subscription.getDurationInMonths());
        expectedSubscriptionDetails.setNumberOfRequestsAllowed(this.subscription.getNumberOfRequestsAllowed());
        expectedSubscriptionDetails.setStartDate(startDateString);
        expectedSubscriptionDetails.setEndDate(endDateString);
        expectedAcctResponse.setSubscriptionDetails(expectedSubscriptionDetails);


        when(this.dateTimeServiceMock.getCurrentDateAndTime()).thenReturn(this.dateNow);
        when(this.dateTimeServiceMock.getStringFormattedDate(this.subscription.getStartDate())).thenReturn(startDateString);
        when(this.dateTimeServiceMock.getStringFormattedDate(this.subscription.getEndDate())).thenReturn(endDateString);

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
        this.account.setAccountSubscription(null);
        final AccountResponse expectedAcctResponse = new AccountResponse();
        expectedAcctResponse.setUsername(this.account.getName());
        expectedAcctResponse.setActive(this.account.getActive());
        expectedAcctResponse.setPackageType(this.packageType.getName());
        expectedAcctResponse.setRole(this.role.getName());
        expectedAcctResponse.setPrivileges(Arrays.asList(this.privilege.getName()));
        expectedAcctResponse.setAccountKey(this.account.getAccountKey());

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
        this.privilege = new Privilege();
        this.privilege.setName("testFeature");

        final Set<Privilege> privileges = new HashSet<>();
        privileges.add(this.privilege);

        this.packageType = new PackageType();
        this.packageType.setId(2);
        this.packageType.setName("testPackageType");
        this.packageType.setPrivilegeSet(privileges);
        this.packageType.setAllowedMonthlyRequests(300);

        this.role = new Role();
        this.role.setId(3);
        this.role.setName("testRole");

        this.subscription = new AccountSubscription();
        this.subscription.setId((long) 4);
        this.subscription.setDurationInMonths(3);
        this.subscription.setNumberOfRequestsAllowed(300);

        final Date startDate = this.dateNow.getTime();
        this.subscription.setStartDate(startDate);

        this.dateNow.add(Calendar.MONTH, 3);
        final Date endDate = this.dateNow.getTime();
        this.subscription.setEndDate(endDate);


        this.account = new Account();
        this.account.setId((long) 100);
        this.account.setName("testUserName");
        this.account.setActive(true);
        this.account.setPackageType(this.packageType);
        this.account.setRole(this.role);
        this.account.setAccountKey("3ed5tHgn");
        this.account.setAccountSubscription(this.subscription);


    }

}
