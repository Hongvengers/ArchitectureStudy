package hongvengers.architecturestudy.account.application.port.out;

import hongvengers.architecturestudy.account.domain.Account;

public interface UpdateAccountStatePort {
    void updateActivities(Account account);
}
