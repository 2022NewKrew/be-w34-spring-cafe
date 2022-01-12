package com.kakao.cafe.service;

import com.kakao.cafe.dto.MemberCreateDto;
import com.kakao.cafe.dto.MemberDto;

import java.util.List;

public interface MemberService {
    void save(MemberCreateDto memberCreateDto);

    List<MemberDto> getMemberList();
    MemberDto getMember(String userId);
}
