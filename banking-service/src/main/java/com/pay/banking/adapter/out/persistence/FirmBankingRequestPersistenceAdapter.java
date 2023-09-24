package com.pay.banking.adapter.out.persistence;

import com.pay.banking.application.port.out.RegisterBankAccountPort;
import com.pay.banking.application.port.out.RequestFirmBankingPort;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.banking.domain.RegisteredBankAccount;
import com.pay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@PersistenceAdapter
@RequiredArgsConstructor
public class FirmBankingRequestPersistenceAdapter implements RequestFirmBankingPort {

    private final SpringDataFirmBankingRepository firmBankingRepository;


    @Override
    public FirmBankingJpaEntity createFirmBankingRequest(FirmBankingRequest.FromBankName fromBankName, FirmBankingRequest.FromBankAccountNumber fromBankAccountNumber, FirmBankingRequest.ToBankName toBankName, FirmBankingRequest.ToBankAccountNumber toBankAccountNumber, FirmBankingRequest.MoneyAmount moneyAmount, FirmBankingRequest.FirmBankingStatus firmBankingStatus) {
        FirmBankingJpaEntity entity = firmBankingRepository.save(new FirmBankingJpaEntity(
                fromBankName.getFromBankName(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                toBankName.getToBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                moneyAmount.getMoneyAmount(),
                firmBankingStatus.getFirmBankingStatus(),
                UUID.randomUUID()
        ));
        return entity;
    }

    @Override
    public FirmBankingJpaEntity modifyFirmBankingRequest(FirmBankingJpaEntity entity) {
        return firmBankingRepository.save(entity);
    }
}
