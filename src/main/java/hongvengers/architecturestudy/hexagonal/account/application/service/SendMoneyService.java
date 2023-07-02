package hongvengers.architecturestudy.hexagonal.account.application.service;

import hongvengers.architecturestudy.hexagonal.account.application.port.in.SendMoneyUseCase;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.AccountLock;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.LoadAccountPort;
import hongvengers.architecturestudy.hexagonal.account.application.port.out.UpdateAccountStatePort;
import hongvengers.architecturestudy.hexagonal.account.application.port.in.SendMoneyCommand;
import lombok.RequiredArgsConstructor;
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

}
