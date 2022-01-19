package com.kakao.cafe.dto;

import java.util.List;

public class MemberListDto {
    private int total;
    private List<MemberDto> memberList;

    public MemberListDto(int total, List<MemberDto> memberList) {
        this.total = total;
        this.memberList = memberList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<MemberDto> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<MemberDto> memberList) {
        this.memberList = memberList;
    }
}
