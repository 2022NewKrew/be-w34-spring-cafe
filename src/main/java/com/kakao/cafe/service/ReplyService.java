package com.kakao.cafe.service;

import com.kakao.cafe.dto.ReplyContentsDto;
import com.kakao.cafe.dto.ReplyDto;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ReplyService {
    void insertReply(int aid, String writer, ReplyContentsDto contentsDto) throws SQLException;
    List<ReplyDto> getReplyListOfArticle(int aid);
    void deleteReply(int id);
    String getWriterById(int id) throws NoSuchElementException;
}
