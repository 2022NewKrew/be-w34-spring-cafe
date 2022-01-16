package com.example.kakaocafe.security.filter;

import com.example.kakaocafe.core.meta.SessionData;
import com.example.kakaocafe.security.domain.CsrfTokenContext;
import com.example.kakaocafe.security.util.ResponseBodyWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;

public class CsrfGenerateFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        final HttpMethod requestMethod = HttpMethod.resolve(request.getMethod());
        return requestMethod != HttpMethod.GET;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final ResponseBodyWrapper responseBodyWrapper = new ResponseBodyWrapper(response);

        filterChain.doFilter(request, responseBodyWrapper);

        final String responseBody = responseBodyWrapper.toString();

        final HttpSession httpSession = request.getSession(false);

        final boolean isLogoutState = Objects.isNull(httpSession);
        if (isLogoutState) {
            response.getOutputStream().write(responseBody.getBytes(StandardCharsets.UTF_8));
            return;
        }

        final CsrfTokenContext csrfTokenContext = getCsrfTokenContext(httpSession);
        final byte[] html = generateCsrfToken(responseBody, csrfTokenContext);
        response.getOutputStream().write(html);
    }

    private CsrfTokenContext getCsrfTokenContext(HttpSession httpSession) {
        final CsrfTokenContext csrfTokenContext = (CsrfTokenContext) httpSession.getAttribute(SessionData.CSRF);
        Objects.requireNonNull(csrfTokenContext);
        return csrfTokenContext;
    }

    private byte[] generateCsrfToken(String responseBody, CsrfTokenContext csrfTokenContext) throws IOException {

        final Document doc = Jsoup.parse(responseBody);
        final Elements forms = doc.getElementsByTag("form");

        Objects.requireNonNull(csrfTokenContext);

        forms.forEach(form -> {
            final String url = form.attr("action");
            final UUID csrfToken = csrfTokenContext.generate(url);

            final Element csrfElement = new Element("input");
            csrfElement.attr("type", "hidden");
            csrfElement.attr("name", "_csrf");
            csrfElement.attr("value", csrfToken.toString());

            form.appendChild(csrfElement);
        });

        return doc.html().getBytes(StandardCharsets.UTF_8);
    }
}
