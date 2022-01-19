package com.kakao.cafe.dao;

import com.kakao.cafe.model.Reply;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CafeReplyDaoImpl implements CafeReplyDao {

    DataSource dataSource;
    public CafeReplyDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Reply> getReplyList(int postId) {
        List<Reply> replyList = new ArrayList<>();
        String sql = "SELECT userId, content, createdAt FROM reply\n"
                + "WHERE postId=? and tombstone=false\n"
                + "ORDER BY createdAt\n";
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String userId = rs.getString("userId");
                String content = rs.getString("content");
                String createdAt = rs.getString("createdAt").substring(0,19);

                replyList.add(new Reply(userId, content, createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return replyList;
    }

    @Override
    public boolean submitReply(Reply reply) {
        return false;
    }

    @Override
    public boolean deleteReply(int replyId) {
        return false;
    }
}
