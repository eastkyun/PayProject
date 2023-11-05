package com.pay.money.adapter.out.persistence;

import com.pay.money.domain.MemberMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataMemberMoneyRepository extends JpaRepository<MemberMoneyJpaEntity,Long> {

    @Query("SELECT e FROM MemberMoneyJpaEntity e WHERE e.memberId = :membershipId")
    List<MemberMoneyJpaEntity> findByMembershipId(@Param("membershipId") Long membershipId);
}
