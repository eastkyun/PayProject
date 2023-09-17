package com.pay.membership.application.service;

import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipMapper;
import com.pay.membership.application.port.in.RegisterMembershipCommand;
import com.pay.membership.application.port.in.RegisterMembershipUseCase;
import com.pay.membership.application.port.out.RegisterMembershipPort;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import common.UseCase;

@UseCase
@RequiredArgsConstructor
@Transactional
public class RegisterMembershipService implements RegisterMembershipUseCase {

    private final RegisterMembershipPort registerMembershipPort;

    private final MembershipMapper membershipMapper;

    @Override
    public Membership registerMembership(RegisterMembershipCommand command) {
        // command -> db

        // biz logic -> db

        // external system
        // port, adapter
        MembershipJpaEntity jpaEntity = registerMembershipPort.createMembership(
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsCorp(command.isCorp()),
                new Membership.MembershipIsValid(command.isValid())
        );
        System.out.println(jpaEntity.toString());
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
