package com.pay.membership.adapter.out.persistence;

import com.pay.membership.domain.Membership;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper {
    public static Membership mapToDomainEntity(MembershipJpaEntity membershipJpaEntity){
        return Membership.generateMember(
                new Membership.MembershipId(membershipJpaEntity.getMembershipId()+""),
                new Membership.MembershipName(membershipJpaEntity.getName()),
                new Membership.MembershipAddress(membershipJpaEntity.getAddress()),
                new Membership.MembershipEmail(membershipJpaEntity.getEmail()),
                new Membership.MembershipIsCorp(membershipJpaEntity.isCorp()),
                new Membership.MembershipIsValid(membershipJpaEntity.isValid()));
    }
}
