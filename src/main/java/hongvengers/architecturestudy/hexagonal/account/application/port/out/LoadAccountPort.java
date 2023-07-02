package hongvengers.architecturestudy.hexagonal.account.application.port.out;

import hongvengers.architecturestudy.hexagonal.account.domain.Account;
import hongvengers.architecturestudy.hexagonal.account.domain.Account.AccountId;

import java.time.LocalDateTime;

public interface LoadAccountPort {
    Account loadAccount(AccountId accountId, LocalDateTime now);
}
