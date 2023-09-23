package com.pay.banking.application.service;

import com.pay.banking.adapter.out.external.bank.BankAccount;
import com.pay.banking.adapter.out.external.bank.GetBankAccountRequest;
import com.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.pay.banking.adapter.out.persistence.RegisteredBankAccountMapper;
import com.pay.banking.application.port.in.RegisterBankAccountCommand;
import com.pay.banking.application.port.in.RegisterBankAccountUseCase;
import com.pay.banking.application.port.out.RegisterBankAccountPort;
import com.pay.banking.application.port.out.RequestBankAccountInfoPort;
import com.pay.banking.domain.RegisteredBankAccount;
import com.pay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterBankAccountService implements RegisterBankAccountUseCase {

    private final RegisterBankAccountPort registerBankAccountPort;

    private final RegisteredBankAccountMapper registeredBankAccountMapper;

    private final RegisteredBankAccountMapper mapper;

    private final RequestBankAccountInfoPort requestBankAccountInfoPort;

    @Override
    public RegisteredBankAccount registerBankAccount(RegisterBankAccountCommand command) {
        // 은행 계좌를 등록해야하는 서비스

        // 멤버 서비스 확인은 skip

        // 1. 외부 실제 은행에 등록이 가능한 계좌인지 확인
        // 외부 은행에 이 계좌 정상 확인 필요
        // biz logic -> ex system
        // port -> adapter -> ex

        // 실제 외부 은행 계좌 정보 get
        BankAccount accountInfo = requestBankAccountInfoPort.getBankAccountInfo(
                new GetBankAccountRequest(
                        command.getBankName(),
                        command.getBankAccountNumber()
        ));

        boolean accountIsValid = accountInfo.isValid();
        if (accountIsValid){
            // 등록 정보 저장
            RegisteredBankAccountJpaEntity savedAccountInfo = registerBankAccountPort.createRegisteredBankAccount(
                    new RegisteredBankAccount.MembershipId(command.getMembershipId()),
                    new RegisteredBankAccount.BankName(command.getBankName()),
                    new RegisteredBankAccount.BankAccountNumber(command.getBankAccountNumber()),
                    new RegisteredBankAccount.IsValid(command.isValid())
            );

            return mapper.mapToDomainEntity(savedAccountInfo);
        } else{
            return null;
        }
    }
}
