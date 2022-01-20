package com.kakao.cafe.model.dto;

import com.kakao.cafe.model.vo.UserVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private int id;
    private UserVo writer;
    private String contents;
}
