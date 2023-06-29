package hongvengers.architecturestudy.account.adapter.web;

import hongvengers.architecturestudy.account.application.port.in.SendMoneyCommand;
import hongvengers.architecturestudy.account.application.port.in.SendMoneyUseCase;
import hongvengers.architecturestudy.account.domain.Account;
import hongvengers.architecturestudy.account.domain.Account.AccountId;
import hongvengers.architecturestudy.account.domain.Money;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final SendMoneyUseCase sendMoneyUseCase;

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    void sendMoney(@PathVariable("sourceAccountId") Long sourceAccountId, @PathVariable("targetAccountId") Long targetAccountId, @PathVariable("amount") Long amount) {
        SendMoneyCommand command = new SendMoneyCommand(new AccountId(sourceAccountId), new AccountId(targetAccountId), Money.of(amount));
        sendMoneyUseCase.sendMoney(command);
    }
}
