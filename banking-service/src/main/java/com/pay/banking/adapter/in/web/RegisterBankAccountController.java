package com.pay.banking.adapter.in.web;

import com.pay.banking.application.port.in.RegisterBankAccountCommand;
import com.pay.banking.application.port.in.RegisterBankAccountUseCase;
import com.pay.banking.domain.RegisteredBankAccount;
import com.pay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@WebAdapter
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/banking/account/register" )
    RegisteredBankAccount registerMembership(@RequestBody RegisterBankAccountRequest request){
        // request -> command
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .isValid(request.isValid())
                .build();
        // UseCase
        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerBankAccount(command);

        if (registeredBankAccount==null){
            throw new RuntimeException("등록 실패");
        }
        return registeredBankAccount;
    }
}
