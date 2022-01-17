package com.kakao.cafe.controller.viewdto.response;

import com.kakao.cafe.article.service.dto.ArticleReadServiceResponse;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ArticleReadResponse extends HashMap<String, Object> {
    public ArticleReadResponse(ArticleReadServiceResponse dto) {
        this.put("title", dto.getTitle());
        this.put("authorstringid", dto.getAuthorStringId());
        this.put("writedate", dto.getMakeTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.put("contents", dto.getContents());
    }
}
