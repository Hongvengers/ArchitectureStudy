package hongvengers.architecturestudy.hexagonal.account.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Money {
    private Long amount;

    public Money(Long amount) {
        this.amount = amount;
    }

    public static Money of(Long amount) {
        return new Money(amount);
    }
}
