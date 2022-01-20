package com.kakao.cafe.service;

import com.kakao.cafe.dto.CommentDto;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.NoSuchElementException;

public interface CommentService {
    void add(@NonNull final CommentDto commentDto);
    List<CommentDto> getDtoList(final long articleIdx, final String currentUserId);
    CommentDto getDto(final long idx) throws NoSuchElementException;
    boolean update(@NonNull final CommentDto commentDto);
    boolean delete(@NonNull final long idx);
}
