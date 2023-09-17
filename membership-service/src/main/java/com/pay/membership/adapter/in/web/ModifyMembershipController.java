package com.pay.membership.adapter.in.web;

import com.pay.common.WebAdapter;
import com.pay.membership.application.port.in.ModifyMembershipCommand;
import com.pay.membership.application.port.in.ModifyMembershipUseCase;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class ModifyMembershipController {
    private final ModifyMembershipUseCase modifyMembershipUseCase;

    @PostMapping(path="/membership/modify")
    ResponseEntity<Membership> modifyMembershipByMemberId(@RequestBody ModifyMembershipRequest request){
        ModifyMembershipCommand command = ModifyMembershipCommand.builder()
                .membershipId(request.getMembershipId())
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isCorp(request.isCorp())
                .isValid(request.isValid())
                .build();
        return ResponseEntity.ok(modifyMembershipUseCase.modifyMembership(command));

    }
}
