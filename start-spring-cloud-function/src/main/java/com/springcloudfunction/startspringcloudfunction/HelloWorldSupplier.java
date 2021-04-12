package com.springcloudfunction.startspringcloudfunction;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class HelloWorldSupplier implements Supplier<String> {

    @Override
    public String get() {
        return new StringBuilder("Ola").append(" mundo").toString();
    }
}
