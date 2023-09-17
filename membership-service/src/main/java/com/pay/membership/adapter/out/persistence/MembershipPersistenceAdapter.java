package com.pay.membership.adapter.out.persistence;

import com.pay.common.PersistenceAdapter;
import com.pay.membership.application.port.out.FindMembershipPort;
import com.pay.membership.application.port.out.ModifyMembershipPort;
import com.pay.membership.application.port.out.RegisterMembershipPort;
import com.pay.membership.domain.Membership;
import lombok.RequiredArgsConstructor;


@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort
                                                   , FindMembershipPort
                                                   , ModifyMembershipPort {

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

    @Override
    public MembershipJpaEntity findMembership(Membership.MembershipId membershipId) {
        return membershipRepository.getById(Long.parseLong(membershipId.getMembershipId()));
    }

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId,
                                                Membership.MembershipName membershipName,
                                                Membership.MembershipEmail membershipEmail,
                                                Membership.MembershipAddress membershipAddress,
                                                Membership.MembershipIsCorp membershipIsCorp,
                                                Membership.MembershipIsValid membershipIsValid) {
        MembershipJpaEntity entity = findMembership(membershipId);
        entity.setName(membershipName.getName());
        entity.setEmail(membershipEmail.getEmail());
        entity.setAddress(membershipAddress.getAddress());
        entity.setCorp(membershipIsCorp.isCorp());
        entity.setValid(membershipIsValid.isValid());
        return membershipRepository.save(entity);
    }
}
