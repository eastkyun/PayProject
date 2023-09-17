package com.pay.membership.domain;

import lombok.*;

import javax.validation.Valid;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Membership {
    @Getter
    private final String membershipId;
    @Getter
    private final String name;
    @Getter
    private final String address;
    @Getter
    private final String email;
    @Getter
    private final Boolean isValid;
    @Getter
    private final Boolean isCorp;

    public static Membership generateMember(MembershipId membershipId,
                                            MembershipName membershipName,
                                            MembershipAddress membershipAddress,
                                            MembershipEmail membershipEmail,
                                            MembershipIsCorp membershipIsCorp,
                                            MembershipIsValid membershipIsValid){
        return new Membership(
                    membershipId.membershipId,
                    membershipName.Name,
                    membershipAddress.Address,
                    membershipEmail.Email,
                    membershipIsCorp.isCorp,
                    membershipIsValid.isValid
                );
    }


    @Value
    public static class MembershipId{
        public MembershipId(String value) {
            this.membershipId = value;
        }
        String membershipId;
    }
    @Value
    public static class MembershipName{
        public MembershipName(String value){
            this.Name = value;
        }
        String Name;
    }
    @Value
    public static class MembershipEmail{
        public MembershipEmail(String value){
            this.Email = value;
        }
        String Email;
    }
    @Value
    public static class MembershipAddress{
        public MembershipAddress(String value){
            this.Address = value;
        }
        String Address;
    }
    @Value
    public static class MembershipIsValid{
        public MembershipIsValid(boolean value){
            this.isValid = value;
        }
        boolean isValid;
    }
    @Value
    public static class MembershipIsCorp{
        public MembershipIsCorp(boolean value){
            this.isCorp = value;
        }
        boolean isCorp;
    }

}
