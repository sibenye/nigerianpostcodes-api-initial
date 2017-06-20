package service;

import com.elsynergy.nigerianpostcodes.mapper.AccountResponseMapper;
import com.elsynergy.nigerianpostcodes.model.DAO.accountentities.*;
import com.elsynergy.nigerianpostcodes.model.enums.PackageEnum;
import com.elsynergy.nigerianpostcodes.model.enums.RoleEnum;
import com.elsynergy.nigerianpostcodes.model.request.AccountSubscribeRequest;
import com.elsynergy.nigerianpostcodes.model.request.RegisterAccountRequest;
import com.elsynergy.nigerianpostcodes.repo.accountentities.AccountRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.PackageRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.RoleRepository;
import com.elsynergy.nigerianpostcodes.repo.accountentities.SubscriptionRepository;
import com.elsynergy.nigerianpostcodes.service.DateTimeService;
import com.elsynergy.nigerianpostcodes.service.accountentities.AccountService;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest
{
    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AccountResponseMapper acctResponseMapper;

    @Mock
    private DateTimeService dateTimeServiceMock;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private AccountService accountService;

    private Account account;

    private Role role;

    private PackageType packageType;

    private Privilege privilege;

    private DateTimeService dateTimeService;

    @Before
    public void setup()
    {
        this.dateTimeService = new DateTimeService();
        this.getAccountObject();
    }

    @Test
    public void testSubscribeAccount() throws ResourceNotFoundException
    {
        final AccountSubscribeRequest accountSubscribeRequest = new AccountSubscribeRequest();
        accountSubscribeRequest.setAccountName(this.account.getName());
        accountSubscribeRequest.setDurationInMonths(3);

        final Calendar dateNow = this.dateTimeService.getCurrentDateAndTime();

        when(this.accountRepository.findOneByName(this.account.getName()))
        .thenReturn(Optional.of(this.account));
        when(this.dateTimeServiceMock.getCurrentDateAndTime()).thenReturn(dateNow);

        this.accountService.subscribeAccount(accountSubscribeRequest);

        verify(this.accountRepository, times(1)).findOneByName(this.account.getName());
        verify(this.dateTimeServiceMock, times(1)).getCurrentDateAndTime();
        verify(this.subscriptionRepository, times(1)).save(any(Subscription.class));
        verify(this.acctResponseMapper, times(1)).map(any(Account.class));
    }

    @Test
    public void testRegisterAccount()
    {
        final RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setAccountName(this.account.getName());
        registerAccountRequest.setPackageName(PackageEnum.BASIC);

        final Optional<PackageType> packageObj = Optional.of(new PackageType());
        final Optional<Role> roleObj = Optional.of(new Role());

        when(this.packageRepository.findOneByName(anyString())).thenReturn(packageObj);
        when(this.roleRepository.findOneByName(anyString())).thenReturn(roleObj);

        this.accountService.registerAccount(registerAccountRequest);
        verify(this.packageRepository, times(1)).findOneByName(registerAccountRequest.getPackageName().toString());
        verify(this.roleRepository, times(1)).findOneByName(RoleEnum.USER.toString());
        verify(this.accountRepository, times(1)).save(any(Account.class));
        verify(this.acctResponseMapper, times(1)).map(any(Account.class));
    }

    @Test
    public void testGetAccount() throws ResourceNotFoundException
    {
        final String acctName = this.account.getName();
        final Account account = new Account();
        account.setName(acctName);
        final Optional<Account> userObj = Optional.of(account);

        when(this.accountRepository.findOneByName(acctName)).thenReturn(userObj);

        this.accountService.getAccountDetails(acctName);
        verify(this.accountRepository, times(1)).findOneByName(acctName);
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testGetAccountWillThrow() throws ResourceNotFoundException
    {
        final String acctName = this.account.getName();
        final Optional<Account> acctObj = Optional.empty();

        when(this.accountRepository.findOneByName(acctName)).thenReturn(acctObj);

        this.accountService.getAccountDetails(acctName);
        verify(this.accountRepository, times(1)).findOneByName(acctName);
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
        packageType.setAllowedMonthlyRequests(10);
        packageType.setPrivilegeSet(privileges);
        this.packageType = packageType;

        final Role role = new Role();
        role.setId(3);
        role.setName("testRole");
        this.role = role;

        final Account account = new Account();
        account.setId((long) 100);
        account.setName("testAcct");
        account.setActive(true);
        account.setPackageType(this.packageType);
        account.setRole(this.role);
        account.setAccountKey("3ed5tHgn");

        this.account = account;

    }


}
