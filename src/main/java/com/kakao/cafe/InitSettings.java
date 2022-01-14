package com.kakao.cafe;

import com.kakao.cafe.domain.article.*;
import com.kakao.cafe.domain.member.*;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitSettings {

    private final InitService initService;

    @PostConstruct
    public void init() {
        //initService.initMemberMemory();
        //initService.initArticleMemory();
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final MemberRepository memberRepository;
        private final ArticleRepository articleRepository;

        public void initMemberMemory() {
            Member member1 = new Member(new UserId("rubam"), new Name("공돈욱"), new Password("12345"), new Email("wooky9633@naver.com"));
            Member member2 = new Member(new UserId("jaden"), new Name("제이든"), new Password("12345"), new Email("jaden@naver.com"));
            memberRepository.saveMember(member1);
            memberRepository.saveMember(member2);
        }

        public void initArticleMemory() {
            Article article = new Article(new Title("hello world!"), new Text("hello\r\nthis is rubam"), memberRepository.findOne(1L), Time.now());
            article.getComments().add(Comment.createComment(article, Time.now(), new Text("안녕하세요 댓글입니다."), memberRepository.findOne(2L)));
            articleRepository.save(article);
        }
    }
}
