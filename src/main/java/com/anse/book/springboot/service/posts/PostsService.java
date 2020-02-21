package com.anse.book.springboot.service.posts;

import com.anse.book.springboot.domain.posts.Posts;
import com.anse.book.springboot.domain.posts.PostsRepository;
import com.anse.book.springboot.web.dto.PostsResponseDto;
import com.anse.book.springboot.web.dto.PostsSaveRequestsDto;
import com.anse.book.springboot.web.dto.PostsUpdateRequestsDto;
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


    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

        return new PostsResponseDto(entity);
    }

    @Transactional
        public Long update(Long id, PostsUpdateRequestsDto requestsDto) {
            Posts posts = postsRepository.findById(id)
                    .orElseThrow(()-> new IllegalArgumentException("해당 사용자가 없습니다. id = " + id));

            posts.update(requestsDto.getTitle(), requestsDto.getContent());
        
        return id;
    }
}
