package hongvengers.architecturestudy.account.application.service;

import hongvengers.architecturestudy.hexagonal.account.application.port.in.SendMoneyCommand;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.AccountLock;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.LoadAccountPort;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.UpdateAccountStatePort;
import hongvengers.architecturestudy.hexagonal.account.application.service.SendMoneyService;
import hongvengers.architecturestudy.hexagonal.account.domain.Account;
import hongvengers.architecturestudy.hexagonal.account.domain.Account.AccountId;
import hongvengers.architecturestudy.hexagonal.account.domain.Money;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

public class SendMoneyServiceTest {
    private final LoadAccountPort loadAccountPort = Mockito.mock(LoadAccountPort.class);
    private final AccountLock accountLock = Mockito.mock(AccountLock.class);
    private final UpdateAccountStatePort updateAccountStatePort = Mockito.mock(UpdateAccountStatePort.class);
    private final SendMoneyService sendMoneyService = new SendMoneyService(loadAccountPort, accountLock, updateAccountStatePort);

    @Test
    void transactionSucceeds() {
//        AccountId sourceAccountId = new AccountId(41L);
//        Account sourceAccount = givenAnAccountWithId(sourceAccountId);
        Account sourceAccount = givenSourceAccount();

//        AccountId targetAccountId = new AccountId(42L);
//        Account targetAccount = givenAnAccountWithId(targetAccountId);
        Account targetAccount = givenTargetAccount();

        givenWithdrawalWillSucceed(sourceAccount);
        givenDepositWillSucceed(targetAccount);

        Money money = Money.of(500L);

        SendMoneyCommand command = new SendMoneyCommand(
                sourceAccount.getId().get(),
                targetAccount.getId().get(),
                money);

        boolean success = sendMoneyService.sendMoney(command);
        Assertions.assertThat(success).isTrue();

        AccountId sourceAccountId = sourceAccount.getId().get();
        AccountId targetAccountId = targetAccount.getId().get();

        then(accountLock).should().lockAccount(eq(sourceAccountId));
        then(sourceAccount).should().withdraw(eq(money), eq(targetAccountId));
        then(accountLock).should().releaseAccount(eq(sourceAccountId));

        then(accountLock).should().lockAccount(eq(targetAccountId));
        then(targetAccount).should().deposit(eq(money), eq(sourceAccountId));
        then(accountLock).should().releaseAccount(eq(targetAccountId));

        thenAccountsHaveBeenUpdated(sourceAccountId, targetAccountId);
    }

    private void thenAccountsHaveBeenUpdated(AccountId... accountIds) {
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);
        then(updateAccountStatePort).should(times(accountIds.length))
                .updateActivities(accountArgumentCaptor.capture());

        List<AccountId> updatedAccountIds = accountArgumentCaptor.getAllValues()
                .stream()
                .map(Account::getId)
                .map(Optional::get)
                .collect(Collectors.toList());

        for (AccountId accountId : accountIds) {
            Assertions.assertThat(updatedAccountIds).contains(accountId);
        }
    }

    private void givenDepositWillSucceed(Account account) {
        given(account.deposit(any(Money.class), any(AccountId.class)))
                .willReturn(true);
    }

    private void givenWithdrawalWillSucceed(Account account) {
        given(account.withdraw(any(Money.class), any(AccountId.class)))
                .willReturn(true);
    }

    private Account givenTargetAccount() {
        return givenAnAccountWithId(new AccountId(42L));
    }

    private Account givenSourceAccount() {
        return givenAnAccountWithId(new AccountId(41L));
    }

    private Account givenAnAccountWithId(AccountId id) {
        Account account = Mockito.mock(Account.class);
        given(account.getId())
                .willReturn(Optional.of(id));
        given(loadAccountPort.loadAccount(eq(account.getId().get()), any(LocalDateTime.class)))
                .willReturn(account);
        return account;
    }
}
