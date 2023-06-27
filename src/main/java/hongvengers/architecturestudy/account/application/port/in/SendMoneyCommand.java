package hongvengers.architecturestudy.account.application.port.in;

import hongvengers.architecturestudy.account.domain.Account;
import hongvengers.architecturestudy.account.domain.Account.AccountId;
import hongvengers.architecturestudy.account.domain.Money;
import hongvengers.architecturestudy.shared.SelfValidating;
import lombok.Getter;

@Getter
public class SendMoneyCommand extends SelfValidating<SendMoneyCommand> {
    private final AccountId sourceAccountId;
    private final AccountId targetAccountId;
    private final Money money;

    public SendMoneyCommand(AccountId sourceAccountId, AccountId targetAccountId, Money money) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.money = money;
        requiredNonNull(sourceAccountId);
        requiredNonNull(targetAccountId);
        requiredNonNull(money);
        requireGreatherThan(money, 0);
    }
}
