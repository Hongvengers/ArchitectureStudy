package hongvengers.architecturestudy.hexagonal.account.adapter.out.persistence;

import hongvengers.architecturestudy.hexagonal.account.application.port.out.LoadAccountPort;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.UpdateAccountStatePort;
import hongvengers.architecturestudy.hexagonal.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class AccountPersistenceAdapter implements LoadAccountPort, UpdateAccountStatePort {
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final AccountMapper accountMapper;

    @Override
    public Account loadAccount(Account.AccountId accountId, LocalDateTime baselineDate) {
        Optional<AccountJpaEntity> byId = accountRepository.findById(accountId.getValue());
    }

    @Override
    public void updateActivities(Account account) {

    }
}
