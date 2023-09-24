package com.pay.banking.application.port.out;

import com.pay.banking.adapter.out.external.bank.ExternalFirmBankingRequest;
import com.pay.banking.adapter.out.external.bank.FirmBankingResult;

public interface RequestExternalFirmBankingPort {
    FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);
}
