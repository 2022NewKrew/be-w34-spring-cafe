package com.kakao.cafe.service;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.dto.ReplyContentsDto;
import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.repository.ReplyRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyServiceImpl(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void insertReply(int aid, String writer, ReplyContentsDto contentsDto) throws SQLException {
        Reply reply = new Reply(0, aid, writer, contentsDto.getContents());

        replyRepository.save(reply);
    }

    // ARTICLE ID = aid 와 연결된 댓글들 리스트 반환
    public List<ReplyDto> getReplyListOfArticle(int aid) {
        List<ReplyDto> replyDtos = new ArrayList<>();

        for (Reply reply : replyRepository.findByAid(aid)) {
            replyDtos.add(new ReplyDto(reply.getId(), reply.getAid(), reply.getWriter(), reply.getContents()));
        }

        return replyDtos;
    }

    public void deleteReply(int id) {
        replyRepository.delete(id);
    }

    public String getWriterById(int id) throws NoSuchElementException {
        Reply reply = replyRepository.findById(id);

        return reply.getWriter();
    }
}
