package com.kakao.cafe.repository.article;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * author    : brody.moon
 * version   : 1.0
 * DB 대신 임시로 사용하는 저장소입니다.
 *
 */
public class ArticleNoBdUseRepository implements Repository<Article, ArticleDTO, Integer> {
    private static final Map<Integer, Article> DB = new HashMap<>();

    @Override
    public Article save(ArticleDTO dto) {
        return null;
    }

    @Override
    public Optional<Article> findByUserId(Integer userId) {
        return Optional.empty();
    }

    @Override
    public List<Article> findAll() {
        return null;
    }

    /**
     * id 를 이용해 article 정보를 받아오는 메서드입니다.
     * @param id        디비 primary value
     * @return          article 정보
     */
    public static Article lookUpUserInfo(int id){
        return DB.get(id);
    }

    /**
     * article 정보를 저장하는 메서드입니다.
     * @param id            디비 primary value
     * @param article       article 정보
     */
    public static void saveUserInto(int id, Article article){
        DB.put(id, article);
    }

    /**
     * 모든 등록 article 정보를 반환하는 메서드입니다.
     * @return  모든 article 정보 리스트     */
    public static List<ArticleDTO> allArticleInfo(){
        List<ArticleDTO> articleList = new ArrayList<>();

        for(Map.Entry<Integer, Article> entry: DB.entrySet()){
            ArticleDTO articleDTO = entry.getValue().toArticleDTO();
            articleDTO.setCommentSize(entry.getValue().commentSize());
            articleDTO.setIndex(entry.getKey());
            articleList.add(articleDTO);
        }

        return articleList;
    }

    public static int size(){
        return DB.size();
    }
}
