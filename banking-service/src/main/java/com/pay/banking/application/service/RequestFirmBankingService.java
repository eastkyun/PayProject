package com.pay.banking.application.service;

import com.pay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.pay.banking.adapter.out.external.bank.FirmBankingResult;
import com.pay.banking.adapter.out.persistence.FirmBankingJpaEntity;
import com.pay.banking.adapter.out.persistence.FirmBankingMapper;
import com.pay.banking.application.port.in.RegisterFirmBankingUseCase;
import com.pay.banking.application.port.in.RequestFirmBankingCommand;
import com.pay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.pay.banking.application.port.out.RequestFirmBankingPort;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RequestFirmBankingService implements RegisterFirmBankingUseCase {

    private final FirmBankingMapper mapper;
    private final RequestFirmBankingPort requestFirmBankingPort;
    private final RequestExternalFirmBankingPort requestExternalFirmBankingPort;
    @Override
    public FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command) {
        // 비즈니스 로직
        // 1. a->b 계좌

        // 요청에 대한 정보 먼저 write
        FirmBankingJpaEntity requestEntity = requestFirmBankingPort.createFirmBankingRequest(
                new FirmBankingRequest.FromBankName(command.getFromBankName()),
                new FirmBankingRequest.FromBankAccountNumber(command.getFromBankAccountNumber()),
                new FirmBankingRequest.ToBankName(command.getToBankName()),
                new FirmBankingRequest.ToBankAccountNumber(command.getToBankAccountNumber()),
                new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
                new FirmBankingRequest.FirmBankingStatus(0)
        );

        // 외부 은행 펌뱅킹 요청
        FirmBankingResult result = requestExternalFirmBankingPort.requestExternalFirmBanking(new ExternalFirmBankingRequest(
                command.getFromBankName(),
                command.getFromBankAccountNumber(),
                command.getToBankName() ,
                command.getToBankAccountNumber()
        ));

        UUID randomUUID = UUID.randomUUID();
        requestEntity.setUuid(randomUUID.toString());

        // 결과에 따라서 1번에서 작성했던 request update
        if (result.getResultCode() == 0){

            requestEntity.setFirmBankingStatus(1);

        }
        else {
            requestEntity.setFirmBankingStatus(2);
        }

        // 결과 리턴
        return mapper.mapToDomainEntity(requestFirmBankingPort.modifyFirmBankingRequest(requestEntity), randomUUID);
    }
}
