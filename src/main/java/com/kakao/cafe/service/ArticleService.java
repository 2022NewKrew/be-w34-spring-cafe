package com.kakao.cafe.service;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.repository.Repository;
import com.kakao.cafe.repository.article.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;

public class ArticleService implements Service<Article, ArticleDTO, Integer>{
    private final Repository<Article, ArticleDTO, Integer> articleRepository;

    @Autowired
    public ArticleService(Repository<Article, ArticleDTO, Integer> articleRepository){
        this.articleRepository = articleRepository;
    }

    @Override
    public Integer join(ArticleDTO articleDTO) {
        articleDTO.setDate(LocalDateTime.now());
        articleRepository.save(articleDTO);
        return articleDTO.getId();
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<ArticleDTO> findAllArticles(){
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for(Article article: findAll()){
            ArticleDTO articleDTO = new ArticleDTO(article);

            articleDTO.setComments(findComments(articleDTO.getId()));
            articleDTO.setCommentSize(articleDTO.getComments().size());

            articleDTOList.add(articleDTO);
        }

        return articleDTOList;
    }

    @Override
    public Optional<Article> findOne(Integer id) {
        return articleRepository.findById(id);
    }

    private List<ArticleDTO> findComments(int id){
        Optional<Article> findArticle = articleRepository.findById(id);

        if(findArticle.isPresent()){
            if(articleRepository instanceof ArticleRepository) {
                ArticleRepository articleRepository = (ArticleRepository) this.articleRepository;

                return articleRepository.findComments(id);
            }
        }

        return new ArrayList<>();
    }

    public Optional<ArticleDTO> findAddedCommentArticle(int id){
        Optional<Article> findArticle = articleRepository.findById(id);
        Optional<ArticleDTO> findArticleIncludedComments = Optional.empty();

        if(findArticle.isPresent()){
            ArticleDTO articleDTO = new ArticleDTO(findArticle.get());

            articleDTO.setComments(findComments(id));
            articleDTO.setCommentSize(articleDTO.getComments().size());

            findArticleIncludedComments = Optional.of(articleDTO);
        }

        return findArticleIncludedComments;
    }

    @Override
    public void delete(Integer id) {
        articleRepository.delete(id);
    }

    public void deleteArticle(int id){
        Optional<ArticleDTO> optArticle = findAddedCommentArticle(id);

        if(optArticle.isEmpty()){
            return;
        }

        ArticleDTO articleDTO = optArticle.get();

        if(articleDTO.getParent() != -1){
            throw new IllegalStateException("deleteArticle 메서드에는 댓글이 아닌 게시글 Id 로만 접근 할 수 있습니다. (" + id + ") is not article");
        }

        for(ArticleDTO comment: articleDTO.getComments()){
            if(!comment.getWriter().equals(articleDTO.getWriter()))
                return;
        }
        for(ArticleDTO comment: articleDTO.getComments()){
            articleRepository.delete(comment.getId());
        }
        articleRepository.delete(articleDTO.getId());
    }

    @Override
    public void update(ArticleDTO articleDTO) {
        articleRepository.update(articleDTO);
    }

    public void updateArticle(ArticleDTO articleDTO) throws InputMismatchException {
        articleRepository.findById(articleDTO.getId())
                .orElseThrow(() -> new InputMismatchException(articleDTO.getId() + "로 등록된 게시글을 찾울 수 없습니다."));

        update(articleDTO);
    }
}
