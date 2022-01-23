package com.kakao.cafe.interceptor;

import com.kakao.cafe.domain.model.Article;
import com.kakao.cafe.domain.model.User;
import com.kakao.cafe.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ReplySameUserInterceptor implements HandlerInterceptor {

    private final ReplyService replyService;

    public final List<String> sameUserEssential = Arrays.asList("/reply/*/*");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object value = request.getSession().getAttribute("sessionedUser");

        User user = (User) value;
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        String replyId = (String) pathVariables.get("replyId");
        if (replyId == null || replyId.isBlank()) return false;

        String userId = replyService.findUserIdOfReply(replyId);

        return user.isSameUser(userId);
    }
}
