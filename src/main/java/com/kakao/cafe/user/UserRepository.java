package com.kakao.cafe.user;

import java.sql.SQLException;
import java.util.List;

/**
 * 회원 정보에대한 Repository Interface 입니다.
 * @author jm.hong
 */
public interface UserRepository {
    /**
     * 회원 정보를 저장합니다.
     * @param user 회원 정보 엔티티
     * @return Database 에 저장된 key 값
     */
    Long save(User user) throws SQLException;

    /**
     * key 값에 해당하는 회원정보를 반환합니다.
     * @param id 회원 테이블의 key 값
     * @return 회원 정보 엔티티
     */
    User findOne(Long id);

    /**
     * 모든 회원정보 엔티티 리스트를 반환합니다.
     * @return 모든 회원정보 리스트
     */
    List<User> findAll();

    /**
     * user.id에 담긴 key 값에 해당하는 데이터를 현재 파라미터의 user 로 업데이트합니다.
     *
     * @param user 변경된 정보가 담긴 user 엔티티 [key값이 담겨있어야 합니다.]
     * @return 업데이트의 성공 여부 [성공시 : true, 실패시 : false]
     */
    boolean update(User user);
}
