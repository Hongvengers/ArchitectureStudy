package hongvengers.architecturestudy.hexagonal.account.application.port.in;

import hongvengers.architecturestudy.hexagonal.account.domain.Account;
import hongvengers.architecturestudy.hexagonal.account.domain.Money;
import hongvengers.architecturestudy.hexagonal.shared.SelfValidating;
import lombok.Getter;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {
    private final Account.AccountId sourceAccountId;
    private final Account.AccountId targetAccountId;
    private final Money money;

    public SendMoneyCommand(Account.AccountId sourceAccountId, Account.AccountId targetAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        requiredNonNull(sourceAccountId);
        requiredNonNull(targetAccountId);
        requiredNonNull(money);
        requireGreatherThan(money, 0);
    }
}
