package com.pay.membership.application.service;

import com.pay.common.UseCase;
import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipMapper;
import com.pay.membership.application.port.in.FindMembershipCommand;
import com.pay.membership.application.port.in.FindMembershipUseCase;
import com.pay.membership.application.port.out.FindMembershipPort;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class FindMembershipService implements FindMembershipUseCase {

    private final FindMembershipPort findMembershipPort;

    private final MembershipMapper mapper;

    @Override
    public Membership findMembership(FindMembershipCommand command) {
        MembershipJpaEntity entity = findMembershipPort.findMembership(
                new Membership.MembershipId(command.getMembershipId())
        );
        return mapper.mapToDomainEntity(entity);
    }
}
