package com.pay.membership.adapter.in.web;

import com.pay.common.WebAdapter;
import com.pay.membership.application.port.in.FindMembershipCommand;
import com.pay.membership.application.port.in.FindMembershipUseCase;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
public class FindMembershipController {
    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path="/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable String membershipId){
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();
        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));

    }
}
