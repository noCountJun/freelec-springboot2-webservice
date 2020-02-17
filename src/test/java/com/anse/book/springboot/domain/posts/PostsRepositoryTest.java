package com.anse.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    @SpringBootTest
        -별도의 설정없이 H2 데이터베이스를 자동을 실행시켜줌
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    /*
        @After
            - Junit에서 단위테스트가 끝날때마다 수행되는 메서드
     */
    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        /*
            테이블 posts에 insert/update 쿼리를 실행
            id가 있으면 update , 없으면 insert
         */
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("anse@gmail.com")
                .build()
        );

        //when
        /*
            테이블 posts에 있는 모든 데이터 조회
         */
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
