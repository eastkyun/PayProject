package com.pay.banking.adapter.out.external.bank;

import com.pay.banking.application.port.out.RequestBankAccountInfoPort;
import com.pay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@ExternalSystemAdapter
@RequiredArgsConstructor
public class BackAccountAdapter implements RequestBankAccountInfoPort {
    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {
        // 실제 외부 은행 http 요청해야함
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
