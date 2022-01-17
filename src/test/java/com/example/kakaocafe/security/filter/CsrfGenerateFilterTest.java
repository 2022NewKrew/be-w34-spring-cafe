package com.example.kakaocafe.security.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.HTML;

import static org.junit.jupiter.api.Assertions.*;

class CsrfGenerateFilterTest {

    @Test
    void doFilter() {

        final String str = "<!DOCTYPE html>\n" +
                "<html lang=\"kr\">\n" +
                "<head>\n" +
                "    {{>layout/loadMetaData}}\n" +
                "    <link href=\"/css/user/login.css\" rel=\"stylesheet\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"background\">\n" +
                "    {{>layout/header}}\n" +
                "\n" +
                "    <div class=\"container\" id=\"main\">\n" +
                "        <header class=\"post-header\">\n" +
                "            <p class=\"title\">로그인</p>\n" +
                "        </header>\n" +
                "        <form name=\"question\" class=\"info-container\" method=\"post\" action=\"/users/login\">\n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"email\">이메일</label>\n" +
                "                <input type=\"email\" class=\"form-control\" id=\"email\" name=\"email\" placeholder=\"이메일을 입력하세요\">\n" +
                "            </div>\n" +
                "            <div class=\"form-group\">\n" +
                "                <label for=\"password\">비밀번호</label>\n" +
                "                <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\" placeholder=\"비밀번호를 입력하세요\">\n" +
                "            </div>\n" +
                "            <button type=\"submit\" class=\"btn btn-success clearfix pull-right\">완료</button>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>\n";


        final Document doc = Jsoup.parse(str);

        final Elements elements = doc.getElementsByTag("form");
        Element element = elements.get(0).appendChild(new Element(HTML.Tag.INPUT.toString()));
        System.out.println(element);
    }
}
