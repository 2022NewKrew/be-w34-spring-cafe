package com.kakao.cafe.aop;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.service.ArticleService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Aspect
public class CheckArticleAuthAop {
    private final ArticleService articleService;
    Logger logger = LoggerFactory.getLogger(CheckArticleAuthAop.class);

    @Autowired
    public CheckArticleAuthAop(ArticleService articleService){
        this.articleService = articleService;
    }

    @Around("@annotation(ArticleAuthCheck)")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        int id = -1;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = signature.getParameterNames();
        String userId = "";
        for(int i = 0 ; i < paramNames.length ; i++){
            String paramName = paramNames[i];

            if(paramName.equals("id")) {
                id = (int) joinPoint.getArgs()[i];

                Optional<Article> find = articleService.findOne(id);

                if(find.isPresent()){
                    Article article = find.get();

                    userId = article.getWriter();
                }
            }
        }

        HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        Object value = session.getAttribute("sessionedUser");
        String signedUserId = "";

        if(value != null)
            signedUserId = ((UserAccount)value).getUserId();

        if(!userId.equals(signedUserId)){
            logger.error("[checkArticleAuth] 권한 확인 중 잘못된 권한으로 접근하였습니다");
            return "redirect:/";
        }


        return joinPoint.proceed();
    }
}
