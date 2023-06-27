package hongvengers.architecturestudy.account.application.port.in;

import hongvengers.architecturestudy.account.domain.Account.AccountId;
import hongvengers.architecturestudy.account.domain.Money;

public interface GetAccountBalanceQuery {
    Money getAccountBalance(AccountId accountId);
}
