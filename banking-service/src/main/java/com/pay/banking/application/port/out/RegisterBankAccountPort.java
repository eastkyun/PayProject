package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.pay.banking.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {
    RegisteredBankAccountJpaEntity createRegisteredBankAccount(
            RegisteredBankAccount.MembershipId membershipId,
            RegisteredBankAccount.BankName bankName,
            RegisteredBankAccount.BankAccountNumber bankAccountNumber,
            RegisteredBankAccount.IsValid isValid
    );
}
