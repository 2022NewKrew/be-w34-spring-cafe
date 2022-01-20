package com.kakao.cafe.dao;

import com.kakao.cafe.model.Post;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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
        String sql = "INSERT INTO post(userId, title, content)\n"
                + "VALUES (?,?,?)";

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
        String sql = "SELECT postId, userId, title, content, view, createdAt FROM post\n"
                + "WHERE tombstone=false\n"
                + "ORDER BY createdAt DESC\n";

        try(Connection conn = dataSource.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                int postId = rs.getInt("postId");
                String userId = rs.getString("userId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int view = rs.getInt("view");
                String createdAt = rs.getString("createdAt").substring(0,10);

                postList.add(new Post(postId,userId,title,content,view,createdAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return postList;
    }

    @Override
    @Transactional
    public Post getPostContent(int postId) {
        Post selectedPost = null;
        String viewSql = "UPDATE post SET view=view+1\n"
                + "WHERE postId=?";
        String sql = "SELECT userId, title, content, view, SUBSTR(createdAt,1,19) AS createdAt FROM post\n"
                + "WHERE postId=? and tombstone=false";

        try ( Connection conn = dataSource.getConnection() ) {
            PreparedStatement viewPstmt = conn.prepareStatement(viewSql);
            viewPstmt.setInt(1,postId);
            int viewRs = viewPstmt.executeUpdate();

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();

            if(rs.next() && viewRs > 0) {
                String userId = rs.getString("userId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                int view = rs.getInt("view");
                String createdAt = rs.getString("createdAt");

                selectedPost = new Post(postId,userId,title,content,view,createdAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 수동 롤백
        }
        return selectedPost;
    }

    @Override
    public Post postViewEdit(int postId) {
        Post selectedPost = null;
        String sql = "SELECT userId, title, content FROM post\n"
                + "WHERE postId=? and tombstone=false";

        try (Connection conn = dataSource.getConnection() ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String userId = rs.getString("userId");
                String title = rs.getString("title");
                String content = rs.getString("content");

                selectedPost = new Post(postId,userId,title,content);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return selectedPost;
    }

    @Override
    public boolean editPost(int postId, Post post) {
        String sql = "UPDATE post\n"
                + "SET title=?, content=?\n"
                + "WHERE postId=? and userId=?";

        try (Connection conn = dataSource.getConnection() ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, post.getTitle());
            pstmt.setString(2, post.getContent());
            pstmt.setInt(3, postId);
            pstmt.setString(4, post.getUserId());

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
    public boolean deletePost(int postId, String userId) {
        String sql = "UPDATE post\n"
                + "SET tombstone=true\n"
                + "WHERE postId=? and userId=?";

        try (Connection conn = dataSource.getConnection() ) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, postId);
            pstmt.setString(2, userId);

            int updateCnt = pstmt.executeUpdate();
            if( updateCnt > 0 ) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
