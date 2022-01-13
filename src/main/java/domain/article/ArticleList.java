package domain.article;

import java.util.ArrayList;
import java.util.List;

public class ArticleList {

    private final List<Article> articleList;

    private ArticleList() {
        articleList = new ArrayList<>();
    }

    public int size() {
        return articleList.size();
    }

    private static class LazyHolder {
        private static final ArticleList USER_LIST = new ArticleList();
    }

    public static ArticleList getInstance() {
        return ArticleList.LazyHolder.USER_LIST;
    }

    public List<Article> getCopiedArticleList() {
        return new ArrayList<>(articleList);
    }

    public void add(Article article) {
        articleList.add(article);
    }

    public Article getArticleByIndex(int index) {
        return articleList.get(index - 1);
    }

    @Override
    public String toString() {
        return "ArticleList{" +
                "articleList=" + articleList +
                '}';
    }
}
