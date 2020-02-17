package com.anse.book.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class HelloResponseDtoTest {
    @Test
    public void rombokTest() {
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        /*
            assertThat
                - assertj 라는 테스트 검증 라이브러리의 검증 메서드
                - 검증하고 싶은 대상을 메서드 인자로 받음
                - 메서드 체이닝이 지원되어 isEqualTo와 같이 이어서 사용 가능
            isEqualTo
                - assertj의 동등 비교 메서드
                - 두개의 값이 같을 때만 성공
         */
    }
}