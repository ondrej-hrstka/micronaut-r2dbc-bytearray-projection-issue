package com.example;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

import java.util.UUID;

@MappedEntity
public class Entity {

    @Id
    private final UUID id;
    private final byte[] content;

    public Entity(UUID id, byte[] content) {
        this.id = id;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public byte[] getContent() {
        return content;
    }
}
