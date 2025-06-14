package com.ll.demo03.global.Initdata;

import com.ll.demo03.domain.article.article.entity.Article;
import com.ll.demo03.domain.article.article.servic.ArticleService;
import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.member.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.List;

// !prod == dev or test
@Profile("!prod")
@Configuration
@RequiredArgsConstructor
public class NotProd {
    @Lazy
    @Autowired
    private NotProd self;
    private final ArticleService articleService;
    private final MemberService memberService;

    @Bean
    @Order(4)
    public ApplicationRunner initNotProd() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {
        if (articleService.count() > 0) {
            return;
        }

        Member memberUser1 = memberService.findByUsername("user1").get();
        Member memberUser2 = memberService.findByUsername("user2").get();

        Article article1  = articleService.write(memberUser1, "제목 1", "내용 1").getData();
        Article article2 = articleService.write(memberUser1,"제목 2", "내용 2").getData();


        Article article3 = articleService.write(memberUser2, "제목 3", "내용 3").getData();
        Article article4 = articleService.write(memberUser2, "제목 4", "내용 4").getData();
    }


}