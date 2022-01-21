package com.kakao.cafe.controller.articles.api;

import com.kakao.cafe.common.authentification.Auth;
import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.common.session.SessionKeys;
import com.kakao.cafe.controller.articles.dto.response.ReplyResponse;
import com.kakao.cafe.controller.articles.mapper.ArticleViewMapper;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.article.dto.ReplyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/articles")
public class ApiArticleController {

    private final ArticleService articleService;
    private final ArticleViewMapper articleViewMapper;

    @Auth
    @PostMapping("{articleId}/replies")
    public ReplyResponse writeReply(@PathVariable Long articleId,
                                    String comment,
                                    @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        ReplyInfo reply = articleService.writeReply(articleId, loginInfo.getUserId(), comment);
        return articleViewMapper.toReplyResponse(reply);
    }
}
