package com.example.kakaocafe.security.util;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.*;

public class ResponseBodyWrapper extends HttpServletResponseWrapper {

    private final StringWriter buffer;

    public ResponseBodyWrapper(HttpServletResponse response) {
        super(response);
        buffer = new StringWriter();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(buffer);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
