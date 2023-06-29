package hongvengers.architecturestudy.account.application.service;

import hongvengers.architecturestudy.account.application.port.in.SendMoneyUseCase;
import hongvengers.architecturestudy.account.application.port.out.LoadAccountPort;
import hongvengers.architecturestudy.account.application.port.out.UpdateAccountStatePort;
import hongvengers.architecturestudy.account.application.port.in.SendMoneyCommand;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendMoneyService implements SendMoneyUseCase {
    private final LoadAccountPort loadAccountPort;
    private final AccountLock accountLock;
    private final UpdateAccountStatePort updateAccountStatePort;
    @Override
    public boolean sendMoney(SendMoneyCommand command) {
        return false;
    }

    @Value
    private class AccountLock {
    }
}
