package com.example.kakaocafe.domain.post;

import com.example.kakaocafe.domain.post.dto.Post;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDAO postDAO;

    @Transactional
    public void create(WritePostForm writePostForm){
        postDAO.create(writePostForm);
    }

    @Transactional
    public Post findByIdAndPlusViewCount(long id) {
        postDAO.plusViewCount(id);

        return postDAO.getPostById(id)
                .orElseThrow();
    }
}
