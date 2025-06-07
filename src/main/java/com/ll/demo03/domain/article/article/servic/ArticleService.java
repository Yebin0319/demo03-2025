package com.ll.demo03.domain.article.article.servic;

import com.ll.demo03.domain.article.article.entity.Article;
import com.ll.demo03.domain.article.article.repository.ArticleRepository;
import com.ll.demo03.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    public long count() {
        return articleRepository.count();
    }

    // 리턴
    // -이번에 생성된 게시물의 번호
    //-게시물 생성에 대한 결과 메세지
    //-결과 코드
    @Transactional
    public com.ll.demo03.global.rsData.RsData<Article> write(String title, String body) {
        Article article = Article
                .builder()
                .title(title)
                .body(body)
                .build();

        return com.ll.demo03.global.rsData.RsData.of("%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
    }

    @Transactional
    public void delete(Article article1) {
        articleRepository.delete(article1);
    }

    public Optional<Article> findById(long id) {
        return articleRepository.findById(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Transactional
    public com.ll.demo03.global.rsData.RsData<Article> write(Member author, String title, String body) {
        Article article = Article
                .builder()
                .author(author)
                .title(title)
                .body(body)
                .build();

        return com.ll.demo03.global.rsData.RsData.of("%d번 게시물이 작성되었습니다.".formatted(article.getId()), article);
    }
}