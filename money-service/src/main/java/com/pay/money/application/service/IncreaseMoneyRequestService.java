package com.pay.money.application.service;

import com.pay.common.UseCase;
import com.pay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.pay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.pay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.pay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.pay.money.application.port.out.IncreaseMoneyPort;
import com.pay.money.domain.MemberMoney;
import com.pay.money.domain.MoneyChangingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {
    private final MoneyChangingRequestMapper mapper;
    private final IncreaseMoneyPort increaseMoneyPort;
    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {
        // 머니 충전 정의
        // 1. 고객 정보가 정상인지 확인 (멤버)
        // 2. 고객의 연동된 계좌가 있는지?, 고객의 연동된 계좌의 잔액이 충분한지 확인 (뱅킹)
        // 3. 법인 계좌 상태 정상인지 확인 (뱅킹)
        // 4. 증액을 위한 기록 ( MoneyChangingRequest )
        // 5. 펌뱅킹 수행( 고객 연동 계좌 -> 페이 법인 계좌 ) (뱅킹)

        // 6-1. 결과가 정상적이라면, 성공 MoneyChangingRequest 상태값을 변동 후 리턴
        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId()),
                command.getAmount());
        if (memberMoneyJpaEntity != null){
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                    new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                    new MoneyChangingRequest.MoneyChangingType(1),
                    new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                    new MoneyChangingRequest.MoneyChangingStatus(1),
                    new MoneyChangingRequest.Uuid(UUID.randomUUID().toString()))
            );

        }
        // 성공 시의 멤버의 MemberMoney 값 증액 필요(맴버)
        return null;
        // 6-2. 결과가 실패라면 실파라고 상태값 변동 후 리턴

    }
}
