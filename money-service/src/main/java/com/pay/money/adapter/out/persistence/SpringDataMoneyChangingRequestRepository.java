package com.pay.money.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMoneyChangingRequestRepository extends JpaRepository<MoneyChangingRequestJpaEntity,Long> {

}
