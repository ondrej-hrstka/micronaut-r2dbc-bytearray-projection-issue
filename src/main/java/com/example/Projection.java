package com.example;

import io.micronaut.core.annotation.Introspected;

@Introspected
public class Projection {

    private final byte[] content;

    public Projection(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
