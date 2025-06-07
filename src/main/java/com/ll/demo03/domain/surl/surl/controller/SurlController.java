package com.ll.demo03.domain.surl.surl.controller;

import com.ll.demo03.domain.member.member.entity.Member;
import com.ll.demo03.domain.surl.surl.entity.Surl;
import com.ll.demo03.domain.surl.surl.service.SurlService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SurlController {
    private final SurlService surlService;
    private final com.ll.demo03.global.rq.Rq rq;

    @GetMapping("/all")
    @ResponseBody
    public List<Surl> getAll() {
        return surlService.findAll();
    }

    @GetMapping("/add")
    @ResponseBody
    public Surl add(String body, String url) {
        Member member = rq.getMember();

        log.debug("log test");

        return surlService.add(member, body, url);
    }

    @GetMapping("/s/{body}/**")
    @ResponseBody
    public Surl add(
            @PathVariable String body,
            HttpServletRequest req
    ) {
        String url = req.getRequestURI();

        if (req.getQueryString() != null) {
            url += "?" + req.getQueryString();
        }

        String[] urlBits = url.split("/", 4);

        System.out.println("Arrays.toString(urlBits) : " + Arrays.toString(urlBits));

        url = urlBits[3];

        Surl surl = Surl
                .builder()
                .id(++surlsLastId)
                .body(body)
                .url(url)
                .build();
        surls.add(surl);

        return surl;
    }


    @GetMapping("/g/{id}")
    public String go(
            @PathVariable long id
    ) {
        Surl surl = surls
                .stream()
                .filter(_surl -> _surl.getId() == id)
                .findFirst()
                .orElse(null);

        if (surl == null) throw new RuntimeException("%d번 URL을 찾을 수 없습니다.".formatted(id));

        surl.increaseCount();

        return "redirect:" + surl.getUrl();
    }
}