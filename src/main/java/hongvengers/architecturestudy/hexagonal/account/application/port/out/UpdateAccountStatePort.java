package hongvengers.architecturestudy.hexagonal.account.application.port.out;

import hongvengers.architecturestudy.hexagonal.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
