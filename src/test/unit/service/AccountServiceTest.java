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
import com.elsynergy.nigerianpostcodes.web.exception.BadRequestException;
import com.elsynergy.nigerianpostcodes.web.exception.ResourceNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

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

    private AccountSubscription subscription;

    private Calendar dateNow;

    @Before
    public void setup()
    {
        this.dateTimeService = new DateTimeService();
        this.dateNow = this.dateTimeService.getCurrentDateAndTime();
        this.getAccountObject();
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testSubscribeAccountWillThrowForNonExistingAccount() throws ResourceNotFoundException, BadRequestException
    {
        final AccountSubscribeRequest accountSubscribeRequest = new AccountSubscribeRequest();
        accountSubscribeRequest.setAccountName(this.account.getName());
        accountSubscribeRequest.setDurationInMonths(3);

        when(this.accountRepository.findOneByName(this.account.getName()))
        .thenReturn(Optional.empty());

        this.accountService.subscribeAccount(accountSubscribeRequest);

        verify(this.accountRepository, times(1)).findOneByName(this.account.getName());
        verify(this.dateTimeServiceMock, times(0)).getCurrentDateAndTime();
        verify(this.subscriptionRepository, times(0)).save(any(AccountSubscription.class));
        verify(this.accountRepository, times(0)).save(any(Account.class));
        verify(this.acctResponseMapper, times(0)).map(any(Account.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testSubscribeAccountWillThrowForNonExistingPackage() throws ResourceNotFoundException, BadRequestException
    {
        final AccountSubscribeRequest accountSubscribeRequest = new AccountSubscribeRequest();
        accountSubscribeRequest.setAccountName(this.account.getName());
        accountSubscribeRequest.setDurationInMonths(3);
        accountSubscribeRequest.setPackageName(PackageEnum.BASIC);

        when(this.accountRepository.findOneByName(this.account.getName()))
        .thenReturn(Optional.of(this.account));
        when(this.packageRepository.findOneByName(PackageEnum.BASIC.toString()))
        .thenReturn(Optional.empty());

        this.accountService.subscribeAccount(accountSubscribeRequest);

        verify(this.accountRepository, times(1)).findOneByName(this.account.getName());
        verify(this.dateTimeServiceMock, times(0)).getCurrentDateAndTime();
        verify(this.subscriptionRepository, times(0)).save(any(AccountSubscription.class));
        verify(this.accountRepository, times(0)).save(any(Account.class));
        verify(this.acctResponseMapper, times(0)).map(any(Account.class));
    }

    @Test(expected=BadRequestException.class)
    public void testSubscribeAccountWillThrowBadRequest() throws ResourceNotFoundException, BadRequestException
    {
        final AccountSubscribeRequest accountSubscribeRequest = new AccountSubscribeRequest();
        accountSubscribeRequest.setAccountName(this.account.getName());
        accountSubscribeRequest.setDurationInMonths(3);
        accountSubscribeRequest.setPackageName(PackageEnum.BASIC);

        when(this.accountRepository.findOneByName(this.account.getName()))
        .thenReturn(Optional.of(this.account));
        when(this.packageRepository.findOneByName(PackageEnum.BASIC.toString()))
        .thenReturn(Optional.of(this.account.getPackageType()));
        when(this.dateTimeServiceMock.getCurrentDateAndTime()).thenReturn(this.dateNow);

        this.accountService.subscribeAccount(accountSubscribeRequest);

        verify(this.accountRepository, times(1)).findOneByName(this.account.getName());
        verify(this.dateTimeServiceMock, times(0)).getCurrentDateAndTime();
        verify(this.subscriptionRepository, times(1)).save(any(AccountSubscription.class));
        verify(this.accountRepository, times(0)).save(any(Account.class));
        verify(this.acctResponseMapper, times(0)).map(any(Account.class));
    }

    @Test
    public void testSubscribeAccount() throws ResourceNotFoundException, BadRequestException
    {
        final AccountSubscribeRequest accountSubscribeRequest = new AccountSubscribeRequest();
        accountSubscribeRequest.setAccountName(this.account.getName());
        accountSubscribeRequest.setDurationInMonths(3);
        accountSubscribeRequest.setPackageName(PackageEnum.BASIC);
        accountSubscribeRequest.setRenewSubscription(true);

        when(this.accountRepository.findOneByName(this.account.getName()))
        .thenReturn(Optional.of(this.account));
        when(this.packageRepository.findOneByName(PackageEnum.BASIC.toString()))
        .thenReturn(Optional.of(this.account.getPackageType()));
        when(this.dateTimeServiceMock.getCurrentDateAndTime()).thenReturn(this.dateNow);

        this.accountService.subscribeAccount(accountSubscribeRequest);

        verify(this.accountRepository, times(1)).findOneByName(this.account.getName());
        verify(this.dateTimeServiceMock, times(1)).getCurrentDateAndTime();
        verify(this.subscriptionRepository, times(1)).save(any(AccountSubscription.class));
        verify(this.accountRepository, times(1)).save(any(Account.class));
        verify(this.acctResponseMapper, times(1)).map(any(Account.class));
    }

    @Test
    public void testRegisterAccount() throws ResourceNotFoundException
    {
        final RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setAccountName(this.account.getName());
        registerAccountRequest.setPackageName(PackageEnum.BASIC);
        registerAccountRequest.setDurationInMonths(3);

        when(this.packageRepository.findOneByName(anyString())).thenReturn(Optional.of(this.packageType));
        when(this.roleRepository.findOneByName(anyString())).thenReturn(Optional.of(this.role));
        when(this.dateTimeServiceMock.getCurrentDateAndTime()).thenReturn(this.dateNow);

        this.accountService.registerAccount(registerAccountRequest);
        verify(this.packageRepository, times(1)).findOneByName(registerAccountRequest.getPackageName().toString());
        verify(this.roleRepository, times(1)).findOneByName(RoleEnum.USER.toString());
        verify(this.accountRepository, times(1)).save(any(Account.class));
        verify(this.acctResponseMapper, times(1)).map(any(Account.class));
        verify(this.subscriptionRepository, times(1)).save(any(AccountSubscription.class));
    }

    @Test
    public void testRegisterAccountWillNotCallSubscriptionSave() throws ResourceNotFoundException
    {
        final RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setAccountName(this.account.getName());
        registerAccountRequest.setPackageName(null);
        registerAccountRequest.setDurationInMonths(null);

        when(this.roleRepository.findOneByName(anyString())).thenReturn(Optional.of(this.role));

        this.accountService.registerAccount(registerAccountRequest);
        verify(this.packageRepository, times(0)).findOneByName(anyString());
        verify(this.roleRepository, times(1)).findOneByName(RoleEnum.USER.toString());
        verify(this.accountRepository, times(1)).save(any(Account.class));
        verify(this.acctResponseMapper, times(1)).map(any(Account.class));
        verify(this.subscriptionRepository, times(0)).save(any(AccountSubscription.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testRegisterAccountWillThrowForNonExistentPackage() throws ResourceNotFoundException
    {
        final RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setAccountName(this.account.getName());
        registerAccountRequest.setPackageName(PackageEnum.BASIC);
        registerAccountRequest.setDurationInMonths(3);

        when(this.packageRepository.findOneByName(PackageEnum.BASIC.toString())).thenReturn(Optional.empty());
        when(this.roleRepository.findOneByName(anyString())).thenReturn(Optional.of(this.role));

        this.accountService.registerAccount(registerAccountRequest);
        verify(this.packageRepository, times(1)).findOneByName(registerAccountRequest.getPackageName().toString());
        verify(this.roleRepository, times(1)).findOneByName(RoleEnum.USER.toString());
        verify(this.accountRepository, times(0)).save(any(Account.class));
        verify(this.acctResponseMapper, times(0)).map(any(Account.class));
        verify(this.subscriptionRepository, times(0)).save(any(AccountSubscription.class));
    }

    @Test(expected=ResourceNotFoundException.class)
    public void testRegisterAccountWillThrowForNonExistentRole() throws ResourceNotFoundException
    {
        final RegisterAccountRequest registerAccountRequest = new RegisterAccountRequest();
        registerAccountRequest.setAccountName(this.account.getName());
        registerAccountRequest.setPackageName(PackageEnum.BASIC);
        registerAccountRequest.setRole(RoleEnum.USER);
        registerAccountRequest.setDurationInMonths(3);

        when(this.roleRepository.findOneByName(RoleEnum.USER.toString())).thenReturn(Optional.empty());

        this.accountService.registerAccount(registerAccountRequest);
        verify(this.packageRepository, times(0)).findOneByName(registerAccountRequest.getPackageName().toString());
        verify(this.roleRepository, times(1)).findOneByName(RoleEnum.USER.toString());
        verify(this.accountRepository, times(0)).save(any(Account.class));
        verify(this.acctResponseMapper, times(0)).map(any(Account.class));
        verify(this.subscriptionRepository, times(0)).save(any(AccountSubscription.class));
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
