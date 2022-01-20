package com.kakao.cafe.service;

import com.kakao.cafe.domain.entity.Reply;
import com.kakao.cafe.domain.repository.reply.ReplyRepository;
import com.kakao.cafe.dto.RequestReplyDto;
import com.kakao.cafe.dto.ResponseReplyDto;
import com.kakao.cafe.exception.ReplyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    /*
     * 새 댓글 작성
     */
    public void createReply(long authorId, long articleId, RequestReplyDto replyDto) {
        Reply reply = modelMapper.map(replyDto, Reply.class);
        reply.setAuthorId(authorId);
        reply.setArticleId(articleId);
        reply.setCreatedAt(new Date());
        replyRepository.save(reply);
    }

    /*
     * 게시글 id로 게시글에 작성된 전체 댓글 조회
     */
    public List<ResponseReplyDto> getReplyOfArticle(long articleId) {
        return replyRepository.findAllByArticleId(articleId).stream()
                .map(reply -> modelMapper.map(reply, ResponseReplyDto.class))
                .collect(Collectors.toList());
    }

    /*
    * id로 댓글 작성자 id 조회
    */
    public long getAuthorIdOfReply(long replyId){
        Reply reply = replyRepository.findById(replyId).orElseThrow(ReplyNotFoundException::new);
        return reply.getAuthorId();
    }

    /*
    * id로 댓글 수정
    */
    public void updateReply(long id, RequestReplyDto replyDto) {
        Reply reply = replyRepository.findById(id).orElseThrow(ReplyNotFoundException::new);
        reply.setContent(replyDto.getContent());
        replyRepository.save(reply);
    }

    /*
    * id로 댓글 삭제
    */
    public void deleteReply(long id) {
        replyRepository.delete(id);
    }
}
