package com.pay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 무조건 충전
public class IncreaseMoneyChangingRequest {
    private String targetMembershipId;
    private int amount;
}
