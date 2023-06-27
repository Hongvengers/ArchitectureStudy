package hongvengers.architecturestudy.account.application.port.out;

import hongvengers.architecturestudy.account.domain.Account;
import hongvengers.architecturestudy.account.domain.Account.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId, LocalDateTime now);
}
