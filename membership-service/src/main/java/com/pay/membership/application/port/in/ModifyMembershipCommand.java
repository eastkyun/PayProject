package com.pay.membership.application.port.in;

import com.pay.common.SelfValidating;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class ModifyMembershipCommand extends SelfValidating<FindMembershipCommand> {


    @NotNull
    private final String membershipId;

    @NotNull
    private final String name;

    @NotNull
    @NotBlank
    private final String address;

    @NotNull
    @NotBlank
    private final String email;

    private final boolean isValid;

    private final boolean isCorp;

    public ModifyMembershipCommand(String membershipId, String name,  String address, String email,boolean isValid, boolean isCorp) {
        this.membershipId =membershipId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.isValid = isValid;
        this.isCorp = isCorp;
        this.validatedSelf();
    }
}
