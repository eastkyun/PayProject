package com.pay.banking.application.port.in;

import com.pay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestFirmBankingCommand extends SelfValidating<RegisterBankAccountCommand> {
    @NotNull
    @NotBlank
    private final String fromBankName;

    @NotNull
    @NotBlank
    private final String fromBankAccountNumber;

    @NotNull
    @NotBlank
    private final String toBankName;

    @NotNull
    @NotBlank
    private final String toBankAccountNumber;

    private final int moneyAmount;

    public RequestFirmBankingCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int moneyAmount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
        this.validatedSelf();
    }
}
