package hongvengers.architecturestudy.hexagonal.account.application.service;

import hongvengers.architecturestudy.hexagonal.account.application.port.in.GetAccountBalanceQuery;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.LoadAccountPort;
import hongvengers.architecturestudy.hexagonal.account.domain.Account;
import hongvengers.architecturestudy.hexagonal.account.domain.Money;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class GetAccountBalanceService implements GetAccountBalanceQuery {
    private final LoadAccountPort loadAccountPort;
    @Override
    public Money getAccountBalance(Account.AccountId accountId) {
        return loadAccountPort.loadAccount(accountId, LocalDateTime.now())
                .calculateBalance();
    }
}
