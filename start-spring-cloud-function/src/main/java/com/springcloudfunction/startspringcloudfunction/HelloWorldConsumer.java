package com.springcloudfunction.startspringcloudfunction;

import java.util.function.Consumer;
import java.util.function.Function;

public class HelloWorldConsumer implements Consumer<String> {

    @Override
    public void accept(String s) {
        System.out.println(s.toUpperCase());
    }
}
