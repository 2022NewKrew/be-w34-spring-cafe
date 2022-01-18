package com.kakao.cafe.web.utility;

import org.springframework.jdbc.core.namedparam.AbstractSqlParameterSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class CombinedSqlParameterSource extends AbstractSqlParameterSource {
    private MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
    private BeanPropertySqlParameterSource beanPropertySqlParameterSource;

    public CombinedSqlParameterSource(Object object) {
        this.beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(object);
    }

    public void addValue(String paramName, Object value) {
        mapSqlParameterSource.addValue(paramName, value);
    }

    @Override
    public boolean hasValue(String paramName) {
        return beanPropertySqlParameterSource.hasValue(paramName) || mapSqlParameterSource.hasValue(paramName);
    }

    @Override
    public Object getValue(String paramName) {
        return beanPropertySqlParameterSource.hasValue(paramName) ? beanPropertySqlParameterSource.getValue(paramName) : mapSqlParameterSource.getValue(paramName);
    }

    @Override
    public int getSqlType(String paramName) {
        return beanPropertySqlParameterSource.hasValue(paramName) ? beanPropertySqlParameterSource.getSqlType(paramName) : mapSqlParameterSource.getSqlType(paramName);
    }
}
