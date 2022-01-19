package com.kakao.cafe.domain;

import com.kakao.cafe.dto.ArticleDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class Article {
    private long key;
    private User author;
    private String title;
    private String content;
    private LocalDateTime postTime;
    private List<Comment> commentList;
    private boolean deleted;

    public static Article fromDTOWithoutPostTime(ArticleDTO articleDTO) {
        Article article = Article.builder()
                .key(articleDTO.getKey())
                .author(User.fromDTO(articleDTO.getAuthor()))
                .title(articleDTO.getTitle())
                .content(articleDTO.getContent())
                .build();
        return article;
    }

    public static Article fromDTOWithoutUser(ArticleDTO articleDTO) {
        Article article = Article.builder()
                .key(articleDTO.getKey())
                .title(articleDTO.getTitle())
                .content(articleDTO.getContent())
                .build();
        return article;
    }

    public ArticleDTO getDTO() {
        ArticleDTO articleDTO = ArticleDTO.builder()
                .key(key)
                .author(author.getDTO())
                .title(title)
                .content(content)
                .postTime(postTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .build();
        if (commentList != null)
            articleDTO.setCommentDTOList(commentList.stream().map(Comment::getDTO).collect(Collectors.toList()));
        return articleDTO;
    }


}
