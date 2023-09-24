package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.persistence.FirmBankingJpaEntity;
import com.pay.banking.adapter.out.persistence.RegisteredBankAccountJpaEntity;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.banking.domain.RegisteredBankAccount;

public interface RequestFirmBankingPort {
    FirmBankingJpaEntity createFirmBankingRequest(
            FirmBankingRequest.FromBankName fromBankName,
            FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber,
            FirmBankingRequest.ToBankName toBankName,
            FirmBankingRequest.ToBankAccountNumber toBankAccountNumber,
            FirmBankingRequest.MoneyAmount moneyAmount,
            FirmBankingRequest.FirmBankingStatus firmBankingStatus
    );
    FirmBankingJpaEntity modifyFirmBankingRequest(
            FirmBankingJpaEntity entity
    );
}
