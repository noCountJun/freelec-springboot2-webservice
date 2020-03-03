package com.anse.book.springboot.web;

import com.anse.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
    @RunWith(SpringRunner.class)
        - 스프링 부트 테스터와 Junit 사이에 연결자 역할
        - SpringRunner라는 스프링 실행자를 실행

    @WebMvcTest
        - Web(Spring MVC) 에 집중할 수 있는 어노테이션
        - @Controller, @ControllerAdvice 등을 사용할 수 있으나 @Service, @Component, @Repository는 사용 못함
*/
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;    // 웹 API 테스트 할 때 사용하고 스프링 MVC테스트의 시작점
                            // 이를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다

    @WithMockUser(roles = "USER")
    @Test
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))      // MockMvc를 통해 /hello 주소로 HTTP get 요청
                .andExpect(status().isOk())         // HTTP Header의 Status를 검증 (200인지)
                .andExpect(content().string(hello));// 응답 본문의 내용을 검증
                                                    // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.name", is(name)))
                    .andExpect(jsonPath("$.amount", is(amount))
                );

        /*
            .param
                - API 테스트할때 요청 파라미터 (String 만 가능)
            .jsonPath
                - JSON 응답값을 필드별로 검증할 수 있는 메서드
                - $를 기준으로 필드명을 명시하니 $.name,$.amount로 검증
         */
    }
}
