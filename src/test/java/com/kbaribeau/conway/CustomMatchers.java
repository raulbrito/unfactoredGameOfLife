package com.kbaribeau.conway;

import org.hamcrest.Matcher;

public class CustomMatchers {
    public static Matcher<Game> isCompletelyDead() {
        return new IsCompletelyDead();
    }
}
