package hongvengers.architecturestudy.hexagonal.account.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Account {

    private AccountId id;
    private Money baselineBalance;
    private ActivityWindow activityWindow;


    public static Account withoutId(Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(null, baselineBalance, activityWindow);
    }

    public static Account withId(AccountId accountId, Money baselineBalance, ActivityWindow activityWindow) {
        return new Account(accountId, baselineBalance, activityWindow);
    }

    public Optional<AccountId> getId() {
        return Optional.ofNullable(this.id);
    }

    public Money calculateBalance() {
        return Money.add(
                this.baselineBalance,
                this.activityWindow.calculateBalance(this.id));
    }

    public boolean withdraw(Money money, AccountId targetAccountId) {
        if (!mayWithdraw(money)) {
            return false;
        }

        Activity withdrawl = new Activity(
                this.id,
                this.id,
                targetAccountId,
                LocalDateTime.now(),
                money
        );
        this.activityWindow.addActivity(withdrawl);
        return true;
    }

    private boolean mayWithdraw(Money money) {
        return Money.add(
                this.calculateBalance(),
                money.negate())
        ).isPositive();
    }

    public boolean deposit(Money money, AccountId sourceAccountId) {

    }

    @Value
    public static class AccountId {
        private Long value;
    }
}
