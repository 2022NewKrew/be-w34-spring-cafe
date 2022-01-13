package com.kakao.cafe.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * 회원가입폼에 대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class UserCreateDto {
    @NotBlank(message = "회원아이디를 입력해주세요.")
    private String userId;
    @NotEmpty
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    private String password;
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @NotEmpty
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    /**
     * 회원가입시 DTO의 유효성검사의 에러메세지를 반환합니다.
     * @param errors
     * @return
     */
    public static Map<String, String> validateHandling(Errors errors) {
        Map<String, String> result = new HashMap<>();

        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            result.put(validKeyName, error.getDefaultMessage());
        }

        return result;
    }
}
