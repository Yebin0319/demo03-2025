package com.ll.demo03.global.Initdata;

import com.ll.demo03.domain.article.article.entity.Article;
import com.ll.demo03.domain.article.article.servic.ArticleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;

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

    @Bean
    public ApplicationRunner initNotProd() {
        return args -> {
            self.work1();
            self.work2();
        };
    }

    @Transactional
    public void work1() {
        if (articleService.count() > 0) {
            return;
        }

        Article article1  = articleService.write("제목 1", "내용 1");
        Article article2 = articleService.write("제목 2", "내용 2");

        article2.setTitle("제목!!");

        articleService.delete(article1);
    }

    @Transactional
    public void work2() {
        // List: 0 ~ N
        // Optional : 0 ~ 1
        Article article = articleService.findById(2L).get();
        List<Article> articles = articleService.findAll();


    }
}
