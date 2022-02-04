package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleReplyForm;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleServiceTests {

	private final static Logger logger = LoggerFactory.getLogger(ArticleServiceTests.class);

	@Autowired
	private ArticleService articleService;

	private static Long articleNum;

	@Test
	public void articleService_addArticle() {
		SampleArticleForm testForm = new SampleArticleForm("testTitle", "testContent");
		Article returnArticle =  articleService.addArticle("testUser", testForm);
		articleNum = returnArticle.getArticleID();
		assertEquals("testUser", returnArticle.getAuthor());
		logger.info("articleNum : {}" ,articleNum);
	}

	@Test
	public void articleService_findArticle() {
		logger.info("articleNum : {}", articleNum);
		assertEquals("testUser", articleService.findArticle(articleNum).getAuthor());
		assertEquals("testTitle", articleService.findArticle(articleNum).getTitle());
		assertEquals("testContent", articleService.findArticle(articleNum).getContent());
	}

	@Test
	public void articleService_updateArticle() {
		SampleArticleForm updateForm = new SampleArticleForm("testTitleUpdate", "testContentUpdate");
		articleService.updateArticle(articleNum, updateForm);
		assertEquals("testUser", articleService.findArticle(articleNum).getAuthor());
		assertEquals("testTitleUpdate", articleService.findArticle(articleNum).getTitle());
		assertEquals("testContentUpdate", articleService.findArticle(articleNum).getContent());
	}

	@Test
	public void articleService_deleteArticle() {
		ExpectedException expectedException = ExpectedException.none();
		articleService.deleteArticle(articleNum);
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("사용자 정보가 존재하지 않습니다.");
	}

}
