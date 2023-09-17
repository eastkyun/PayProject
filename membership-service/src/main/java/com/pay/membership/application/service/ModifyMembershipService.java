package com.pay.membership.application.service;

import com.pay.common.UseCase;
import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipMapper;
import com.pay.membership.application.port.in.ModifyMembershipCommand;
import com.pay.membership.application.port.in.ModifyMembershipUseCase;
import com.pay.membership.application.port.out.ModifyMembershipPort;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase{

    private final ModifyMembershipPort modifyMembershipPort;

    private final MembershipMapper membershipMapper;
    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsCorp(command.isCorp()),
                new Membership.MembershipIsValid(command.isValid())
        );

        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
