package com.kakao.cafe.util;

import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtils {
    private JdbcUtils() { }

    public static Connection getConnection(DataSource dataSource) {
        return DataSourceUtils.getConnection(dataSource);
    }

    public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            if(pstmt != null){
                pstmt.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        try {
            if(conn != null){
                conn.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
