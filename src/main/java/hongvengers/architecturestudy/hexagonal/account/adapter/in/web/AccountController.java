package hongvengers.architecturestudy.hexagonal.account.adapter.in.web;

import hongvengers.architecturestudy.hexagonal.account.application.port.in.SendMoneyCommand;
import hongvengers.architecturestudy.hexagonal.account.application.port.in.SendMoneyUseCase;
import hongvengers.architecturestudy.hexagonal.account.domain.Money;
import hongvengers.architecturestudy.hexagonal.account.domain.Account;
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
        SendMoneyCommand command = new SendMoneyCommand(new Account.AccountId(sourceAccountId), new Account.AccountId(targetAccountId), Money.of(amount));
        sendMoneyUseCase.sendMoney(command);
    }
}
