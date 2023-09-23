package com.pay.banking.adapter.out.persistence;

import com.pay.banking.domain.RegisteredBankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataRegisteredBankAccountRepository extends JpaRepository<RegisteredBankAccountJpaEntity,Long> {

}
