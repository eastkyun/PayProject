package com.pay.money.adapter.out.persistence;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "member_money")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberMoneyJpaEntity {
    @Id
    @GeneratedValue
    private Long memberMoneyId;
    private Long memberId;
    private int balance;

    public MemberMoneyJpaEntity(Long memberId, int balance) {
        this.memberId = memberId;
        this.balance = balance;
    }
}
