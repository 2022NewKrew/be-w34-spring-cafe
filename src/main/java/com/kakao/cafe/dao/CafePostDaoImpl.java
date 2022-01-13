package com.kakao.cafe.dao;

import com.kakao.cafe.model.Post;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CafePostDaoImpl implements CafePostDao {

    DataSource dataSource;

    public CafePostDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean writePost(Post newPost) {
        String sql = "INSERT INTO post(userId, title, content)\n";
        sql += "VALUES (?,?,?)";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newPost.getUserId());
            pstmt.setString(2, newPost.getTitle());
            pstmt.setString(3, newPost.getContent());
            int updateCnt = pstmt.executeUpdate();
            if( updateCnt > 0 ) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Post> getPostList() {
        List<Post> postList = new ArrayList<>();
        String sql = "SELECT postId, userId, title, content, SUBSTR(createdAt,1,10) AS createdAt FROM post\n";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int postId = rs.getInt("postId");
                String userId = rs.getString("userId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createdAt = rs.getString("createdAt");

                postList.add(new Post(postId,userId,title,content,createdAt));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postList;
    }

    @Override
    public Post getPostContent(int postId) {
        Post selectedPost = null;
        String sql = "SELECT userId, title, content, SUBSTR(createdAt,1,19) AS createdAt FROM post\n";
        sql += "WHERE postId=?";

        try ( Connection conn = dataSource.getConnection() ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String userId = rs.getString("userId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String createdAt = rs.getString("createdAt");

                selectedPost = new Post(postId,userId,title,content,createdAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedPost;
    }
}
