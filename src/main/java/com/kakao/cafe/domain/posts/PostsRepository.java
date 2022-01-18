package com.kakao.cafe.domain.posts;

import com.kakao.cafe.web.dto.PostResponseDto;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PostsRepository {

    private final String JDBC_DRIVER;
    private final String DB_URL;
    private Connection connection;

    public PostsRepository() {
        JDBC_DRIVER = "org.h2.driver";
        DB_URL = "jdbc:h2:mem:test";

        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "CREATE TABLE post (" +
                    "id BIGINT NOT NULL AUTO_INCREMENT, " +
                    "writer VARCHAR(32), " +
                    "title VARCHAR(32), "+
                    "content VARCHAR(100), " +
                    "created_date DATETIME " +
                    ")";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(PostEntity postEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "INSERT INTO post (writer, title, content, created_date) VALUES (?, ?, ?, ?)";
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, postEntity.getWriter());
            ps.setString(2, postEntity.getTitle());
            ps.setString(3, postEntity.getContent());
            ps.setTimestamp(4, Timestamp.valueOf(postEntity.getTime()));

            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PostResponseDto> findAll() {
        ArrayList<PostResponseDto> postsList = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM post";
            final PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet result =  ps.executeQuery();

            while (result.next()) {
                Long id = result.getLong("id");
                String writer = result.getString("writer");
                String title = result.getString("title");
                String content = result.getString("content");
                Timestamp time = result.getTimestamp("created_date");
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String created_date = dateFormat.format(time);
                postsList.add(new PostResponseDto(writer, title, content, 0, id, created_date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return postsList;
    }

    public PostResponseDto findById(Long id) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "SELECT * FROM post WHERE id = " + id.toString();
            final PreparedStatement ps = connection.prepareStatement(sql);
            final ResultSet result =  ps.executeQuery();
            result.next();
            String writer = result.getString("writer");
            String title = result.getString("title");
            String content = result.getString("content");
            Timestamp time = result.getTimestamp("created_date");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String created_date = dateFormat.format(time);
            PostResponseDto resultDto = new PostResponseDto(writer, title, content, 0, id, created_date);
            return resultDto;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Long id, PostEntity postEntity) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "UPDATE post SET title=?, content=? WHERE id=?";
            final PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, postEntity.getTitle());
            ps.setString(2, postEntity.getContent());
            ps.setLong(3, id);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteById(Long id) {
        try {
            connection = DriverManager.getConnection(DB_URL);
            Statement statement = connection.createStatement();
            final String sql = "DELETE FROM post WHERE id=?";
            final PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);

            ps.execute();
        } catch (SQLException e) {

        }
    }
}
