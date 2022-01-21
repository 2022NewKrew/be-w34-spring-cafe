package com.kakao.cafe.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePageInfoDto {
    private int curStartPage;
    private int curEndPage;
    private List<ArticlePageDto> pageList;
}
