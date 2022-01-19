package com.kakao.cafe.dto.post;

import java.util.List;

public class PostListDto {
    private int total;
    private List<PostListItemDto> postList;

    public PostListDto(int total, List<PostListItemDto> postList) {
        this.total = total;
        this.postList = postList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PostListItemDto> getPostList() {
        return postList;
    }

    public void setPostList(List<PostListItemDto> postList) {
        this.postList = postList;
    }
}
