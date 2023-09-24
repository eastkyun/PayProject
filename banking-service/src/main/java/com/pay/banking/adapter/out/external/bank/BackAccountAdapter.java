package com.pay.banking.adapter.out.external.bank;

import com.pay.banking.application.port.out.RequestBankAccountInfoPort;
import com.pay.banking.application.port.out.RequestExternalFirmBankingPort;
import com.pay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BackAccountAdapter implements RequestBankAccountInfoPort, RequestExternalFirmBankingPort {
    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        // 실제 외부 은행 http 요청해야함
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }

    @Override
    public FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request) {
        // 실제 외부 은행 http 통신을 통해서 펌뱅킹 요청
        // 그 결과 firm banking result 파싱
        return new FirmBankingResult(0);
    }

}
