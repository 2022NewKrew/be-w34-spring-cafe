package com.kakao.cafe.service;

import com.kakao.cafe.dao.ReplyDao;
import com.kakao.cafe.dto.reply.ReplyDto;
import com.kakao.cafe.vo.ReplyVo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyDao replyDao;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public void addReply(ReplyDto replyDto) {
        replyDto.setDate(LocalDateTime.now().format(formatter));
        ReplyVo replyVo = modelMapper.map(replyDto,ReplyVo.class);
        replyDao.save(replyVo);
    }

    public List<ReplyDto> getReplyList(int articleId) {
        List<ReplyVo> replyList = replyDao.findAllByArticleId(articleId);
        return replyList.stream()
                .map(replyVo -> modelMapper.map(replyVo,ReplyDto.class))
                .collect(Collectors.toList());
    }

    public void deleteReply(int replyId) {
        replyDao.deleteByReplyId(replyId);
    }
}
