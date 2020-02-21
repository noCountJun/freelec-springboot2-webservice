package com.anse.book.springboot.domain.posts;

import com.anse.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * @Entity
 *  - 테이블과 링크될 클래스임을 나타낸다
 *  - 카멜케이스이름을 언더스코어 네이밍으로 테이블 이름을 매칭
 *      ex) SaleManager.java  -> sales_manager  (table)
 *
 * @NoArgsConstructor
 *  - 롬복의 어노테이션으로 기본 생성자 자동 추가
 *
 * @Getter
 *  - 모든 필드의 Getter 메서드 생성
 */
@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    /*
        @ Id
            - 해당 테이블의 PK

        @GeneratiedValue
            - PK의 생성 규칙
            -  GenerationType.IDENTITY를 추가해야만 auto_increment가 된다

        @Column
            - 테이블의 컬럼을 나타내며, 굳이 선언하지 않아도 해당클래스의 필드는 모두 컬럼이 된다.
            - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있을때 사용
            - 문자열은 VARCHAR(255)가 기본값이지만,
              사이즈를 늘리거나 (length=500)
              타입을 텍스트로 변경(ex:content)하고 싶을 때 사용  (columnDefinition="TEXT")
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable=false)
    private String title;

    @Column(columnDefinition="TEXT", nullable = false)
    private String content;

    private String author;

    // 롬복 어노테이션으로 해당 클래스의 빌더 패턴 클래스를 생성
    // 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}