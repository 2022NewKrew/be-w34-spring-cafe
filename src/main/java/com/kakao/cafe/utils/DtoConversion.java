package com.kakao.cafe.utils;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import com.kakao.cafe.dto.article.ArticleContentDto;
import com.kakao.cafe.dto.article.ArticleDto;
import com.kakao.cafe.dto.article.ArticleWriteDto;
import com.kakao.cafe.dto.user.SignupDto;
import com.kakao.cafe.dto.user.UserDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class DtoConversion {

    public static User signupDtoToUser(SignupDto dto) {
        UserName userName = new UserName(dto.getUserName());
        Password password = new Password(dto.getPassword());
        Name name = new Name(dto.getName());
        Email email = new Email(dto.getEmail());
        return new User(userName, password, name, email);
    }

    public static List<UserDto> userListToUserDtoList(List<User> userList) {
        return userList.stream()
                .map(DtoConversion::userToUserDto)
                .collect(Collectors.toList());
    }

    public static UserDto userToUserDto(User user) {
        String id = user.getId().toString();
        String userName = user.getUserName().getValue();
        String name = user.getName().getValue();
        String email = user.getEmail().getValue();
        return new UserDto(id, userName, name, email);
    }

    public static List<ArticleDto> articleListToArticleDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(DtoConversion::articleToArticleDto)
                .collect(Collectors.toList());
    }

    public static ArticleDto articleToArticleDto(Article article) {
        String articleId = article.getId().toString();
        String title = article.getTitle().getValue();
        String writer = article.getWriter().getName().getValue();
        String createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int viewCount = article.getViewCount().getValue();

        return new ArticleDto(articleId, title, writer, createdAt, viewCount);
    }

    public static Article articleWriteDtoToArticle(ArticleWriteDto dto, User user) {
        Title title = new Title(dto.getTitle());
        Content content = new Content(dto.getContent());
        return new Article(title, content, user);
    }

    public static ArticleContentDto articleToArticleContentDto(Article article) {
        String title = article.getTitle().getValue();
        String content = article.getContent().getValue();
        String writer = article.getWriter().getUserName().getValue();
        String createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int viewCount = article.getViewCount().getValue();

        return new ArticleContentDto(title, content, writer, createdAt, viewCount);
    }
}
