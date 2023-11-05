package com.pay.money.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// 무조건 충전
public class MoneyChangingResultDetail {
    private String moneyChangingRequestId;
    private int moneyChangingType; //enum 0:증액, 1:감액
    private int moneyChangingResultStatus;
    private int amount;


}
//enum ChangingType{
//    INCREASING, // 증액
//    DECREASING // 감액
//}
//enum MoneyChangingResultStatus{
//    SUCCEEDED, // 성공
//    FAILED, // 실패
//    FAILED_NOT_ENOUGH_MONEY,
//    FAILED_NOT_EXIST_MEMBERSHIP,
//    FAILED_NOT_EXIST_MONEY_CHANGING_REQUEST,
//}