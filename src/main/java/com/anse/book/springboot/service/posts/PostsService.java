package com.anse.book.springboot.service.posts;

import com.anse.book.springboot.domain.posts.PostsRepository;
import com.anse.book.springboot.web.dto.PostsSaveRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDto requestsDto) {
        return postsRepository.save(requestsDto.toEntity()).getId();
    }
}
