package com.kakao.cafe.service;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.JdbcTemplatePostRepository;
import com.kakao.cafe.repository.JdbcTemplateUserRepository;
import com.kakao.cafe.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(JdbcTemplatePostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public String create(Post post){
        postRepository.save(post);
        return post.getTitle();
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Optional<Post> findOne(int Id){
        return postRepository.findById(Id);
    }

    public Optional<Post> findOneByTitle(String title){return postRepository.findByTitle(title);}

    public Optional<Post> findOneByWriter(String writer){return postRepository.findByTitle(writer);}

    public void edit(Post post){
        postRepository.edit(post);
    }

    public void delete(Post post){
        postRepository.delete(post);
    }
}
