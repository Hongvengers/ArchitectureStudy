package hongvengers.architecturestudy.hexagonal.account.application.port.in;

import hongvengers.architecturestudy.hexagonal.account.domain.Money;
import hongvengers.architecturestudy.hexagonal.account.domain.Account;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(Account.AccountId accountId);
}
