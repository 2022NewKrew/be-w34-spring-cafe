package com.kakao.cafe.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class ArticleDTO {
    private String title;
    private String content;
    private String author;
    private LocalDate createdDate;
    private Integer views;
    private Integer articleIdx;
    List<CommentDTO> commentList;
    private Integer commentTotalCount;

    public ArticleDTO(String title, String content) {
        this.title = title;
        this.content = content;
        this.author = "익명";
        this.createdDate = LocalDate.now();
        this.views = 0;
        this.commentList = new ArrayList<>();
        this.commentTotalCount = 0;
    }

    public void addComment(CommentDTO commentDTO){
        commentDTO.setCommentIdx(++this.commentTotalCount);
        this.commentList.add(commentDTO);
    }
}
