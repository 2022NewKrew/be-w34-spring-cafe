package com.kakao.cafe.reply.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kakao.cafe.controller.session.AuthInfo;
import com.kakao.cafe.controller.viewdto.request.ReplyCreateRequest;
import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.service.dto.CreateReplyServiceRequest;
import com.kakao.cafe.reply.service.dto.OneReplyServiceResponse;

public class ReplyServiceDTOMapper {

    public static CreateReplyServiceRequest convertToCreateReplyRequest(ReplyCreateRequest req, AuthInfo authInfo) {
        return CreateReplyServiceRequest.builder()
                                        .articleId(Long.parseLong(req.getArticleId()))
                                        .contents(req.getAnswer())
                                        .authorId(authInfo.getId())
                                        .authorStringId(authInfo.getStringId())
                                        .build();
    }

    public static OneReplyServiceResponse convertToOneReplyData(Reply reply) {
        return OneReplyServiceResponse.builder()
                                      .id(reply.getId())
                                      .contents(reply.getContents())
                                      .writeTime(reply.getWriteTime())
                                      .authorStringId(reply.getAuthorStringId())
                                      .build();
    }

    public static List<OneReplyServiceResponse> convertReplyListToDTO(List<Reply> list) {
        return list.stream().map(ReplyServiceDTOMapper::convertToOneReplyData)
                   .collect(Collectors.toList());
    }
}
