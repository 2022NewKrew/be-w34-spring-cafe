package com.kakao.cafe.dao;

import com.kakao.cafe.model.User;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CafeUserDaoImpl implements CafeUserDao {

    DataSource dataSource;

    public CafeUserDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean signUp(User newUser) {
        String sql = "INSERT INTO member (userId, password, email)\n"
                + "VALUES (?,?,?)";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,newUser.getUserId());
            pstmt.setString(2,newUser.getPassword());
            pstmt.setString(3,newUser.getEmail());

            int updateCnt = pstmt.executeUpdate();
            if( updateCnt == 1 ) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean SignIn(User signInUser) {
        String sql = "SELECT email, password FROM member\n"
                + "WHERE userId=?";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,signInUser.getUserId());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String password = rs.getString("password");

                if(signInUser.getPassword().equals(password)) {
                    signInUser.setEmail(rs.getString("email"));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        String sql = "SELECT email FROM member\n"
                + "WHERE userId=?";

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

    @Override
    public boolean adminProfileEdit(User user, String inputPassword) {
        String sql = "SELECT password FROM member\n"
                + "WHERE userId=?";

        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String password = rs.getString("password");
                return password.equals(inputPassword);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
