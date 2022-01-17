package com.kakao.cafe.user;

import java.sql.SQLException;
import java.util.List;

/**
 * 회원정보에대한 Service Interface 입니다.
 *
 * @author jm.hong
 */
public interface UserService {
    /**
     * 회원정보를 저장하고 데이터베이스의 key 값을 반환합니다.
     * @param user 회원정보 엔티티
     * @return 저장한 엔티티에 대한 key 값 / 실패시 -1 반환
     */
    Long save(User user) throws SQLException;

    /**
     * 해당 key 값에 해당하는 Database 를 반환합니다.
     * @param id 조회할 엔티티의 key 값
     * @return key 값에 해당하는 엔티티를 반환합니다.
     */
    User findOne(Long id);

    /**
     * 모든 회원 정보 엔티티 리스트를 반환합니다.
     * @return 모든 회원 정보 엔티티 리스트
     */
    List<User> findAll();

    /**
     * user 엔티티 객체에 담긴 user.id[key]에 해당하는 Database 를 업데이트 합니다.
     * @param user 변경된 엔티티
     * @return 데이터베이스의 저장 성공 여부 [성공시 : true, 실패시 : false]
     */
    boolean update(User user);

    User loginCheck(String userId, String password);
}
