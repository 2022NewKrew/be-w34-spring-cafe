package com.kakao.cafe.article.service;

import com.kakao.cafe.article.dto.CommentDto;
import com.kakao.cafe.article.dto.CommentRequest;
import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.article.model.Comment;
import com.kakao.cafe.article.repository.CommentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public void addComment(Long articleId, String author, CommentRequest commentRequest) {
        Comment comment = modelMapper.map(commentRequest, Comment.class);
        comment.setArticleId(articleId);
        comment.setAuthor(author);

        commentRepository.save(comment);
    }

    public List<CommentDto> getCommentDto(Long articleId){
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id){
        commentRepository.deleteCommentById(id);
    }

    public boolean isAuthor(Long id, String userId){
        Comment comment = getCommentById(id);
        return comment.getAuthor().equals(userId);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findOneById(id).orElseThrow();
    }
}
