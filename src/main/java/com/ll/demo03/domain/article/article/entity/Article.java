package com.ll.demo03.domain.article.article.entity;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.global.jpa.entity.BaseTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PROTECTED)
public class Article extends BaseTime {
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    @ManyToOne
    // 하나의 회원이 수많은 article
    private Member author;
}