package com.ll.demo03.global.Initdata;

import com.ll.demo03.domain.article.article.entity.Article;
import com.ll.demo03.domain.article.article.servic.ArticleService;
import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.member.member.service.MemberService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.util.List;


@Configuration
@RequiredArgsConstructor
@Slf4j
public class All {
    @Lazy
    @Autowired
    private All self;
    private final MemberService memberService;

    @Bean
    @Order(3)
    public ApplicationRunner initAll() {
        return args -> {
            self.work1();
        };
    }

    @Transactional
    public void work1() {

        log.debug("initAll started");

        if (memberService.count() > 0) {
            return;
        }

        Member memberSystem = memberService.join("system", "1234", "시스템").getData();
        Member memberAdmin = memberService.join("admin", "1234", "관리자").getData();

        Member memberUser1 = memberService.join("user1", "1234", "유저 1").getData();
        Member memberUser2 = memberService.join("user2", "1234", "유저 2").getData();

    }

}