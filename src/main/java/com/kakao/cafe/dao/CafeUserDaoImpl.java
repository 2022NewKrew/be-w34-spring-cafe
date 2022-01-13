package com.kakao.cafe.dao;

import com.kakao.cafe.helper.CollectionHelper;
import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CafeUserDaoImpl implements CafeUserDao {

    DataSource dataSource;

    public CafeUserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void signUp(User newUser) {
        String sql = "INSERT INTO member (userId, password, email)\n";
        sql += "VALUES (?,?,?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newUser.getUserId());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getEmail());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT userId, email FROM member\n";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String userId = rs.getString("userId");
                String email = rs.getString("email");
                userList.add(new User(userId, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserProfile(String userId) {
        User selectedUser = null;
        String sql = "SELECT email FROM member\n";
        sql += "WHERE userId=?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,userId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String email = rs.getString("email");
                selectedUser = new User(userId, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedUser;
    }
}
