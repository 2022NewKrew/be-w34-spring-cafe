package com.kakao.cafe.aop;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class HttpRequestParameter {
    private Map<String, String[]> paramMap;

    public HttpRequestParameter(HttpServletRequest request) {
        paramMap = request.getParameterMap();
        removePasswordInMap();
    }

    private void removePasswordInMap() {
        for(String paramKey: paramMap.keySet()) {
            removeEntryIfKeyEqualToPassword(paramKey);
        }
    }

    private void removeEntryIfKeyEqualToPassword(String paramKey) {
        if(isEqualToPassword(paramKey)) {
            paramMap.remove(paramKey);
        }
    }

    private boolean isEqualToPassword(String paramKey) {
        return paramKey.equalsIgnoreCase("password") || paramKey.equalsIgnoreCase("pw");
    }

    public String getStringOfParameters() {
        if (paramMap.isEmpty()) {
            return "";
        }
        return "[ " + paramMapToString() + "]";
    }

    private String paramMapToString() {
        StringBuilder sb = new StringBuilder();
        paramMap.entrySet()
                .stream()
                .forEach(entry -> writeKeyAndValueOfEntryTo(sb, entry));
        String stringOfParameters = sb.toString();
        return stringOfParameters.substring(0, stringOfParameters.length() - 2);
    }

    private void writeKeyAndValueOfEntryTo(StringBuilder sb, Map.Entry<String, String[]> entry) {
        sb.append(entry.getKey() + " : ");
        for (int i = 0; i < entry.getValue().length; i++) {
            sb.append(entry.getValue()[i] + " ");
        }
        sb.append(", ");
    }
}
