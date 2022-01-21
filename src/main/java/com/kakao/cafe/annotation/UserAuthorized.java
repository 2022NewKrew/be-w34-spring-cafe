package com.kakao.cafe.annotation;

import com.kakao.cafe.domain.auth.Auth;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ReplyDto;
import com.kakao.cafe.exception.SessionNotFoundException;
import com.kakao.cafe.exception.UnauthorizedAccessException;
import com.kakao.cafe.service.ArticleService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserAuthorized {

    AuthCode[] target();

    enum AuthCode {
        SESSION {
            @Override
            public void validate(Map<String, String> pathVariableMap, Auth auth, Object object) {
                if (auth == null) {
                    throw new SessionNotFoundException("로그인 정보가 존재하지 않습니다.");
                }
            }
        },
        USER {
            @Override
            public void validate(Map<String, String> pathVariableMap, Auth auth, Object object) {
                long userId = AuthCode.getValidationKeyOrElseThrow(pathVariableMap, "userId");
                AuthCode.throwIfNotAuthorized(auth, userId);
            }
        },
        ARTICLE {
            @Override
            public void validate(Map<String, String> pathVariableMap, Auth auth, Object object) {
                if (!(object instanceof ArticleService)) {
                    throw new AssertionError("ARTICLE 인증을 위한 ArticleService 인스턴스가 없습니다.");
                }
                ArticleService articleService = (ArticleService) object;
                long articleId = getValidationKeyOrElseThrow(pathVariableMap, "articleId");
                ArticleDto article = articleService.findArticleById(articleId);
                throwIfNotAuthorized(auth, article.getAuthorId());
            }
        },
        REPLY {
            @Override
            public void validate(Map<String, String> pathVariableMap, Auth auth, Object object) {
                if (!(object instanceof ArticleService)) {
                    throw new AssertionError("REPLY 인증을 위한 ArticleService 인스턴스가 없습니다.");
                }
                ArticleService articleService = (ArticleService) object;
                long replyId = getValidationKeyOrElseThrow(pathVariableMap, "replyId");
                ReplyDto reply = articleService.findReplyById(replyId);
                throwIfNotAuthorized(auth, reply.getAuthorId());
            }
        };

        private static long getValidationKeyOrElseThrow(Map<String, String> pathVariableMap, String key) {
            if (!pathVariableMap.containsKey(key)) {
                throw new AssertionError(String.format("인증에 필요한 %s 값이 없습니다.", key));
            }
            return Long.parseLong(pathVariableMap.get(key));
        }

        private static void throwIfNotAuthorized(Auth auth, Long id) {
            if (auth.isNotValidId(id)) {
                throw new UnauthorizedAccessException("인가되지 않은 접근입니다.");
            }
        }

        public abstract void validate(Map<String, String> pathVariableMap, Auth auth, Object object);
    }
}
