package com.pay.membership;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pay.membership.adapter.in.web.FindMembershipRequest;
import com.pay.membership.adapter.in.web.RegisterMembershipRequest;
import com.pay.membership.adapter.out.persistence.MembershipJpaEntity;
import com.pay.membership.adapter.out.persistence.MembershipPersistenceAdapter;
import com.pay.membership.adapter.out.persistence.SpringDataMembershipRepository;
import com.pay.membership.application.port.in.FindMembershipCommand;
import com.pay.membership.application.port.in.FindMembershipUseCase;
import com.pay.membership.application.port.in.RegisterMembershipCommand;
import com.pay.membership.application.port.in.RegisterMembershipUseCase;
import com.pay.membership.domain.Membership;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MembershipTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private FindMembershipUseCase findMembershipUseCase;

    @Autowired
    private RegisterMembershipUseCase registerMembershipUseCase;
    @Test
    public void testGetByMembershipId() throws Exception{
        RegisterMembershipRequest request = new RegisterMembershipRequest(
                "name",
                "email",
                "address",
                true
        );
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isCorp(request.isCorp())
                .isValid(true)
                .build();
        // UseCase
        registerMembershipUseCase.registerMembership(command);

        FindMembershipCommand findCommand = FindMembershipCommand.builder()
                .membershipId("1")
                .build();

        Membership membership = findMembershipUseCase.findMembership(findCommand);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/membership/"+findCommand.getMembershipId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(membership)));

    }
    @Test
    public void testRegisterMembership() throws Exception{
        RegisterMembershipRequest request = new RegisterMembershipRequest(
                "name",
                "email",
                "address",
                true
        );
        Membership membership = Membership.generateMember(
                new Membership.MembershipId("1"),
                new Membership.MembershipName("name"),
                new Membership.MembershipAddress("address"),
                new Membership.MembershipEmail("email"),
                new Membership.MembershipIsCorp(true),
                new Membership.MembershipIsValid(true)
        );
        mockMvc.perform(
                MockMvcRequestBuilders.post("/membership/register/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(membership)));

    }
}
