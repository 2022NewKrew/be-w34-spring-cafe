package com.kakao.cafe.view;

import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalIdException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalEmailException;
import com.kakao.cafe.domain.user.exceptions.IllegalPasswordException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserNameException;
import com.kakao.cafe.domain.user.exceptions.UserIdDuplicationException;
import com.kakao.cafe.domain.user.exceptions.UserNotExistException;

public class ErrorMessage {

    public static String getErrorMessage(Exception e) {
        if (e instanceof IllegalUserIdException) {
            return "ID가 잘못되었습니다.";
        }
        if (e instanceof IllegalPasswordException) {
            return "패스워드가 잘못되었습니다.";
        }
        if (e instanceof IllegalUserNameException) {
            return "이름이 잘못되었습니다.";
        }
        if (e instanceof IllegalEmailException) {
            return "이메일이 잘못되었습니다.";
        }
        if (e instanceof UserIdDuplicationException) {
            return "이미 존재하는 ID 입니다.";
        }
        if (e instanceof IllegalIdException) {
            return "게시글 작성에 실패하였습니다.";
        }
        if (e instanceof IllegalTitleException) {
            return "제목이 잘못되었습니다.";
        }
        if (e instanceof IllegalWriterException) {
            return "작성자 이름이 잘못되었습니다.";
        }
        if (e instanceof IllegalDateException) {
            return "업로드 날짜가 잘못되었습니다.";
        }
        if (e instanceof UserNotExistException) {
            return "존재하지 않는 회원입니다.";
        }
        if (e instanceof ArticleNotExistException) {
            return "존재하지 않는 게시글입니다.";
        }
        return "잘못된 접근입니다.";
    }
}
