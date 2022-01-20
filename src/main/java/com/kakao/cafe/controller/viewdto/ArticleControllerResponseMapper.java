package com.kakao.cafe.controller.viewdto;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kakao.cafe.article.service.dto.AllArticlesListServiceResponse;
import com.kakao.cafe.reply.service.dto.OneReplyServiceResponse;

public class ArticleControllerResponseMapper {
    public static List<Map<String, Object>> getArticleListResponse(AllArticlesListServiceResponse dto){
        List<Map<String, Object>> articles = new ArrayList<>();
        for (AllArticlesListServiceResponse.OneArticleData article : dto.getArticles()) {
            HashMap<String, Object> oneArticle = new HashMap<>();
            oneArticle.put("title", article.getTitle());
            oneArticle.put("author", article.getAuthorStringId());
            oneArticle.put("num", article.getHits());
            oneArticle.put("time", article.getWriteTime());
            oneArticle.put("articleid", article.getId());
            articles.add(oneArticle);
        }
        return articles;
    }

    public static List<Map<String, Object>> getReplyListResponse(List<OneReplyServiceResponse> dto){
        List<Map<String, Object>> replies = new ArrayList<>();
        for (OneReplyServiceResponse oneReply : dto) {
            HashMap<String, Object> replyMap = new HashMap<>();
            replyMap.put("authorStringId", oneReply.getAuthorStringId());
            replyMap.put("replyDate", oneReply.getWriteTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            replyMap.put("replyContent", oneReply.getContents());
            replyMap.put("replyId", oneReply.getId());
            replies.add(replyMap);
        }
        return replies;
    }
}
