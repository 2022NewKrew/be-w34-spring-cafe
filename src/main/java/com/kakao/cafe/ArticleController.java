package com.kakao.cafe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ArticleController {
    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private final DataSource dataSource;

    public ArticleController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostMapping("/questions")
    public String postQuestions(String title, String contents, HttpSession session) {
        logger.info("[postQuestions] title = {}, contents = {}", title, contents);
        List<User> users = UserController.selectAllUsers(dataSource);
        Optional<String> validate = SessionController.checkSession(session, users);
        if(validate.isPresent()) {
            return "user/login";
        }
        User user = (User) session.getAttribute("sessionedUser");
        Article article = new Article(0, user.getName(), title, contents);
        insertArticle(article);

        return "redirect:/";
    }

    private void insertArticle(Article article) {
        logger.info("[insertArticle] article = {}", article);
        final String sql = "INSERT INTO ARTICLE(writer, title, contents) VALUES(?,?,?)";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, article.getWriter());
            stmt.setString(2, article.getTitle());
            stmt.setString(3, article.getContents());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public String getMain(Model model) {
        logger.info("[getMain]");
        List<Map<String, String>> articleList = new ArrayList<>();
        List<Article> articles = selectAllArticles();
        for(int i = 0; i < articles.size(); i++) {
            articleList.add(Map.of("index", Integer.toString(i+1),
                    "title", articles.get(i).getTitle(),
                    "writer", articles.get(i).getWriter()));
        }
        model.addAttribute("articles", articleList);
        return "qna/list";
    }

    private List<Article> selectAllArticles() {
        logger.info("[selectArticle]");
        final String sql = "SELECT id, writer, title, contents FROM ARTICLE";
        List<Article> articles = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sql);
            while(resultSet.next()) {
                articles.add(new Article(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articles;
    }

    @GetMapping("/articles/{index}")
    public String getArticle(@PathVariable String index, Model model, HttpSession session) {
        List<User> users = UserController.selectAllUsers(dataSource);
        Optional<String> validate = SessionController.checkSession(session, users);
        if(validate.isPresent()) {
            return validate.get();
        }
        int id = Integer.parseInt(index);
        Article selectedArticle = select1Article(id);
        if(selectedArticle == null) {
            logger.info("Cannot found article by id = {}", id);
            return "qna/show";
        }
        model.addAttribute("writer", selectedArticle.getWriter());
        model.addAttribute("title", selectedArticle.getTitle());
        model.addAttribute("contents", selectedArticle.getContents());
        model.addAttribute("index", index);
        return "qna/show";
    }

    private Article select1Article(int id) {
        final String sql = "SELECT writer, title, contents FROM ARTICLE WHERE id = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if(resultSet.next()) {
                return new Article(id, resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/qna/form")
    public String qnaForm(HttpSession session, Model model) {
        List<User> users = UserController.selectAllUsers(dataSource);
        Optional<String> validate = SessionController.checkSession(session, users);
        if(validate.isPresent()) {
            return "/user/login";
        }
        model.addAttribute("action", "/questions");
        model.addAttribute("title", "");
        model.addAttribute("contents", "");
        return "/qna/form";
    }

    @GetMapping("/article/{index}/edit")
    public String editArticle(@PathVariable String index, Model model, HttpSession session) {
        logger.info("GET /article/{}/edit", index);
        int id = Integer.parseInt(index);
        Article article = select1Article(id);
        List<User> users = UserController.selectAllUsers(dataSource);
        Optional<String> validate = SessionController.checkSession(session, users);
        User user = (User) session.getAttribute("sessionedUser");
        if(validate.isPresent()) {
            return validate.get();
        }
        if(!article.getWriter().equals(user.getName())) {
            session.setAttribute("errormsg", "다른 사람의 글을 수정할 수 없습니다.");
            return "/user/error";
        }
        logger.info("current username = {}, writer = {}", user.getName(), article.getWriter());
        model.addAttribute("action", "/article/" + index);
        model.addAttribute("title", article.getTitle());
        model.addAttribute("contents", article.getContents());
        model.addAttribute("putmethod", "put");
        return "/qna/form";
    }

    @PutMapping("/article/{index}")
    public String modifyArticle(@PathVariable String index, String title, String contents) {
        logger.info("PUT /article/{}", index);
        int id = Integer.parseInt(index);
        Article article = select1Article(id);
        Article newArticle = new Article(article.getId(), article.getWriter(), title, contents);
        updateArticle(newArticle);
        return "redirect:/";
    }

    private void updateArticle(Article article) {
        final String sql = "UPDATE ARTICLE SET title = ?, contents = ? WHERE id = ?";
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, article.getTitle());
            stmt.setString(2, article.getContents());
            stmt.setInt(3, article.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
