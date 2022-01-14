package com.kakao.cafe.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

@DisplayName("User 테스트")
class UserTest {
    private static final int ALLOWED_LENGTH_USERID = 16;
    private static final int ALLOWED_LENGTH_PASSWORD = 16;
    private static final int ALLOWED_LENGTH_NAME = 8;
    private static final int ALLOWED_LENGTH_EMAIL = 24;

    @DisplayName("User 생성자 테스트")
    @Nested
    class ConstructorTest {

        @DisplayName("올바른 값을 입력받았을 때")
        @Nested
        class LegalTest {
            @DisplayName("올바른 값들이 주어졌을 때 User를 생성하면 예외를 던지지 않는다.")
            @Test
            void legalUserTest() {
                //give
                String userId = "isLegalUserId";
                String password = "isLegalPassword";
                String name = "isLegal";
                String email = "isLegal@email.com";
                //when
                //then
                assertThatCode(() -> new User(userId, password, name, email))
                        .doesNotThrowAnyException();
            }
        }

        @DisplayName("올바르지 못한 값을 입력 받았을 때")
        @Nested
        class IllegalTest {
            @DisplayName("올바르지 못한 userId가 주어졌을 때 User를 생성하면 IllegalArgumentException을 던진다.")
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "thisStringLengthOver" + ALLOWED_LENGTH_USERID,
                            "thisStringLengthOver" + ALLOWED_LENGTH_USERID + "Too",
                            "thisStringLengthOver" + ALLOWED_LENGTH_USERID + "Also"
                    }
            )
            void illegalUserIdTest(String testUserId) {
                //give
                String password = "isLegalPassword";
                String name = "isLegal";
                String email = "isLegal@email.com";
                //when
                //then
                assertThatThrownBy(() -> new User(testUserId, password, name, email))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("올바르지 못한 password가 주어졌을 때 User를 생성하면 IllegalArgumentException을 던진다.")
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "thisStringLengthOver" + ALLOWED_LENGTH_PASSWORD,
                            "thisStringLengthOver" + ALLOWED_LENGTH_PASSWORD + "Too",
                            "thisStringLengthOver" + ALLOWED_LENGTH_PASSWORD + "Also"
                    }
            )
            void illegalPasswordTest(String testPassword) {
                //give
                String userId = "isLegalUserId";
                String name = "isLegal";
                String email = "isLegal@email.com";
                //when
                //then
                assertThatThrownBy(() -> new User(userId, testPassword, name, email))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("올바르지 못한 name이 주어졌을 때 User를 생성하면 IllegalArgumentException을 던진다.")
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "thisOver" + ALLOWED_LENGTH_NAME,
                            "thisOver" + ALLOWED_LENGTH_NAME + "Too",
                            "thisOver" + ALLOWED_LENGTH_NAME + "Also"
                    }
            )
            void illegalNameTest(String testName) {
                //give
                String userId = "isLegalUserId";
                String password = "isLegalPassword";
                String email = "isLegal@email.com";
                //when
                //then
                assertThatThrownBy(() -> new User(userId, password, testName, email))
                        .isInstanceOf(IllegalArgumentException.class);
            }

            @DisplayName("올바르지 못한 email이 주어졌을 때 User를 생성하면 IllegalArgumentException을 던진다.")
            @ParameterizedTest
            @ValueSource(
                    strings = {
                            "thisStringSizeIsOverThe" + ALLOWED_LENGTH_EMAIL,
                            "thisStringSizeIsOverThe" + ALLOWED_LENGTH_EMAIL + "Too",
                            "thisStringSizeIsOverThe" + ALLOWED_LENGTH_EMAIL + "Also"
                    }
            )
            void illegalEmailTest(String testEmail) {
                //give
                String userId = "isLegalUserId";
                String password = "isLegalPassword";
                String name = "isLegal";
                //when
                //then
                assertThatThrownBy(() -> new User(userId, password, name, testEmail))
                        .isInstanceOf(IllegalArgumentException.class);
            }
        }
    }

    @DisplayName("User 내부 메서드 테스트")
    @Nested
    class UserMethodTest {
        @DisplayName("User 생성에 사용할 값과 값을 비교할 UserId가 동일 할 때 true를 반환한다.")
        @Test
        void isUserIdTestWithSameId() {
            //give
            String userId = "testId";
            String testId = "testId";
            String password = "isLegalPassword";
            String name = "isLegal";
            String email = "isLegal@email.com";
            User testUser = new User(userId, password, name, email);
            //when
            //then
            assertThat(testUser.isUserId(testId)).isTrue();
        }

        @DisplayName("User 생성에 사용할 값과 값을 비교할 UserId가 다를 때 false를 반환한다.")
        @Test
        void isUserIdTestWithDifferentId() {
            //give
            String userId = "testId";
            String testId = "notSameId";
            String password = "isLegalPassword";
            String name = "isLegal";
            String email = "isLegal@email.com";
            User testUser = new User(userId, password, name, email);
            //when
            //then
            assertThat(testUser.isUserId(testId)).isFalse();
        }

        @DisplayName("User 생성에 사용할 값과 값을 비교할 password가 같을 때 true를 반환한다.")
        @Test
        void isPasswordTestWithSamePassword() {
            //give
            String userId = "testId";
            String password = "isLegalPassword";
            String testPassword = "isLegalPassword";
            String name = "isLegal";
            String email = "isLegal@email.com";
            User testUser = new User(userId, password, name, email);
            //when
            //then
            assertThat(testUser.isPassword(testPassword)).isTrue();
        }

        @DisplayName("User 생성에 사용할 값과 값을 비교할 password가 다를 때 false를 반환한다.")
        @Test
        void isPasswordTestWithDifferentPassword() {
            //give
            String userId = "testId";
            String password = "isLegalPassword";
            String testPassword = "notSamePassword";
            String name = "isLegal";
            String email = "isLegal@email.com";
            User testUser = new User(userId, password, name, email);
            //when
            //then
            assertThat(testUser.isPassword(testPassword)).isFalse();
        }
    }
}
