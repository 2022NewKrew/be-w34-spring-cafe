package com.kakao.cafe.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.function.Predicate;

public class LambdaMatcher<T> extends TypeSafeMatcher<T> {
    private final Predicate<T> matcher;

    public LambdaMatcher(Predicate<T> matcher) {
        this.matcher = matcher;
    }


    @Override
    protected boolean matchesSafely(T item) {
        return this.matcher.test(item);
    }

    @Override
    public void describeTo(Description description) {

    }

    public static<T> LambdaMatcher<T> lambdaMatcher(Predicate<T> matcher){
        return new LambdaMatcher<>(matcher);
    }
}
