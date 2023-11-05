package com.pay.money.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyChangingRequest {
    @Getter
    private final String moneyChangingRequestId;

    // 어떤 고객의 증액/감액 요청했는지의 멤버 정보
    @Getter
    private final String targetMembershipId;

    // 그 요청이 증액 요청인지 감액요청인지
    @Getter
    private final int changingType;
//    enum ChangingType{
//        INCREASING, // 증액
//        DECREASING // 감액
//    }
    
    // 증액 or 감액 요청의 금액
    @Getter
    private final int changingMoneyAmount;

    // 머니 변액 요청에 대한 상태
    @Getter
    private final int changingMoneyStatus; // enum

//    enum ChangingMoneyStatus{
//        REQUESTED, //요청
//        SUCCEEDED, // 성공
//        FAILED, // 실패
//        CANCELLED // 취소됨
//    }

    @Getter
    private final String uuid;

    @Getter
    private final Date createdAt;


    public static MoneyChangingRequest generateMoneyChangingRequest(
            MoneyChangingRequestId moneyChangingRequestId,
            TargetMembershipId targetMembershipId,
            MoneyChangingType moneyChangingType,
            ChangingMoneyAmount changingMoneyAmount,
            MoneyChangingStatus moneyChangingStatus,
            Uuid uuid
            ){
        return new MoneyChangingRequest(
                moneyChangingRequestId.getMoneyChangingRequestId(),
                targetMembershipId.getTargetMembershipId(),
                moneyChangingType.getChangingType(),
                changingMoneyAmount.getChangingMoneyAmount(),
//                Integer.parseInt(changingMoneyAmount.getChangingMoneyAmount()),
                moneyChangingStatus.getChangingMoneyStatus(),
                uuid.getUuid(),
                new Date()
        );
    }

    @Value
    public static class MoneyChangingRequestId{
        public MoneyChangingRequestId(String value) {
            this.moneyChangingRequestId = value;
        }
        String moneyChangingRequestId;
    }
    @Value
    public static class TargetMembershipId{
        public TargetMembershipId(String value) {
            this.targetMembershipId = value;
        }
        String targetMembershipId;
    }

    @Value
    public static class MoneyChangingType{
        public MoneyChangingType(int value) {
            this.changingType = value;
        }
        int changingType;
    }
    @Value
    public static class ChangingMoneyAmount{
        public ChangingMoneyAmount(int value) {
            this.changingMoneyAmount = value;
        }
        int changingMoneyAmount;
    }
    @Value
    public static class MoneyChangingStatus{
        public MoneyChangingStatus(int value) {
            this.changingMoneyStatus = value;
        }
        int changingMoneyStatus;
    }

    @Value
    public static class Uuid{
        public Uuid(String value) {
            this.uuid = value.toString();
        }
        String uuid;
    }


}
