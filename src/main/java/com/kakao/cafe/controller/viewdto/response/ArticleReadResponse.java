package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.article.service.ArticleReadResponseDTO;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ArticleReadResponse extends HashMap<String, Object> {
    public ArticleReadResponse(ArticleReadResponseDTO dto) {
        this.put("title", dto.getTitle());
        this.put("authorstringid", dto.getAuthorStringId());
        this.put("writedate", dto.getMakeTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.put("contents", dto.getContents());
    }
}
