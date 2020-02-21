package com.anse.book.springboot.web;

import com.anse.book.springboot.service.posts.PostsService;
import com.anse.book.springboot.web.dto.PostsResponseDto;
import com.anse.book.springboot.web.dto.PostsSaveRequestsDto;
import com.anse.book.springboot.web.dto.PostsUpdateRequestsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDto requestsDto) {
        return postsService.save(requestsDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestsDto requestsDto) {
        return postsService.update(id, requestsDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }
}
