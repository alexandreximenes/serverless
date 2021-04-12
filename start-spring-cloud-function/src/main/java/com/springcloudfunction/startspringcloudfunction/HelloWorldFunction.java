package com.springcloudfunction.startspringcloudfunction;

import java.util.function.Function;

public class HelloWorldFunction implements Function<String, String> {
    @Override
    public String apply(String s) {
        return s.toUpperCase();
    }
}
