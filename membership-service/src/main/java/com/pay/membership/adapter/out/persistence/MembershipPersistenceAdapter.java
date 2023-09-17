package com.pay.membership.adapter.out.persistence;

import com.pay.membership.application.port.out.RegisterMembershipPort;
import com.pay.membership.domain.Membership;
import common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;
    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipIsCorp membershipIsCorp,
                                                Membership.MembershipIsValid membershipIsValid) {
        MembershipJpaEntity jpaEntity =  membershipRepository.save(
                new MembershipJpaEntity(
                        membershipName.getName(),
                        membershipEmail.getEmail(),
                        membershipAddress.getAddress(),
                        membershipIsCorp.isCorp(),
                        membershipIsValid.isValid()
                )
        );
        return jpaEntity;
    }
}
