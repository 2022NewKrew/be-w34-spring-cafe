package com.kakao.cafe.service;

import com.kakao.cafe.dto.member.MemberCreateDto;
import com.kakao.cafe.dto.member.MemberDto;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {
    void save(MemberCreateDto memberCreateDto);

    List<MemberDto> getMemberList();

    MemberDto getMember(String userId);

    void login(String userId, String password, HttpSession session);

    void logout(HttpSession session);
}
