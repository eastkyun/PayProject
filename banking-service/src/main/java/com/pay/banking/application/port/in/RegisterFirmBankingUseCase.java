package com.pay.banking.application.port.in;

import com.pay.banking.domain.FirmBankingRequest;

public interface RegisterFirmBankingUseCase {
    FirmBankingRequest requestFirmBanking(RequestFirmBankingCommand command);
}
