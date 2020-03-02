package com.anse.book.springboot.web;

import com.anse.book.springboot.config.auth.LoginUser;
import com.anse.book.springboot.config.auth.dto.SessionUser;
import com.anse.book.springboot.domain.user.User;
import com.anse.book.springboot.service.posts.PostsService;
import com.anse.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        // CustomOAuth2UserService에서 로그인 성공시 세션에 SessionUser를 저장
        // 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다
        // SessionUser user = (SessionUser) httpSession.getAttribute("user");

        // 세션에 값이 있을때만 model 에 userName 등록
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);

        return "posts-update";
    }
}
