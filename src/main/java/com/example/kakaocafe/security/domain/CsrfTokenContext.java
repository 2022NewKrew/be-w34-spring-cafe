package com.example.kakaocafe.security.domain;

import com.example.kakaocafe.security.exception.CsrfException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CsrfTokenContext {
    private final Map<String, UUID> csrfTokenContext = new HashMap<>();

    public void validate(String requestURL, UUID requestToken) {
        final UUID sessionToken = csrfTokenContext.get(requestURL);

        if (!requestToken.equals(sessionToken)) {
            throw new CsrfException();
        }

        csrfTokenContext.clear();
    }

    public UUID generate(String url) {
        final UUID csrfToken = UUID.randomUUID();
        csrfTokenContext.put(url, csrfToken);

        return csrfToken;
    }
}
