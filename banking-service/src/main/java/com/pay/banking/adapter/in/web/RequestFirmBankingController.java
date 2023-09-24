package com.pay.banking.adapter.in.web;

import com.pay.banking.application.port.in.RegisterFirmBankingUseCase;
import com.pay.banking.application.port.in.RequestFirmBankingCommand;
import com.pay.banking.domain.FirmBankingRequest;
import com.pay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class RequestFirmBankingController {
    private final RegisterFirmBankingUseCase registerFirmBankingUseCase;

    @PostMapping(path = "/banking/firmbanking/request" )
    FirmBankingRequest requestFirmBanking(@RequestBody RequestFirmBankingRequest request){
        RequestFirmBankingCommand command = RequestFirmBankingCommand.builder()
                .fromBankName(request.getFromBankName())
                .fromBankAccountNumber(request.getFromBankAccountNumber())
                .toBankName(request.getToBankName())
                .toBankAccountNumber(request.getToBankAccountNumber())
                .moneyAmount(request.getMoneyAmount())
                .build();
        return registerFirmBankingUseCase.requestFirmBanking(command);


    }

}
