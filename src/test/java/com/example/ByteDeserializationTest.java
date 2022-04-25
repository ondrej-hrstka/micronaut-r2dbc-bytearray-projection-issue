package com.example;

import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@MicronautTest(transactional = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ByteDeserializationTest implements TestPropertyProvider {

    @Container
    private static final PostgreSQLContainer POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:13.4");

    @Inject
    EntityR2dbcRepository entityR2dbcRepository;

    @Inject
    EntityJdbcRepository entityJdbcRepository;


    @Test
    public void testR2dbcFindProjection() {
        var entity = new Entity(UUID.randomUUID(), new byte[]{97, 98, 99});

        entityR2dbcRepository.save(entity).block();

        var actual = entityR2dbcRepository.findAllById(entity.getId()).collectList().block();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }

    @Test
    public void testR2dbcFindAll() {
        var entity = new Entity(UUID.randomUUID(), new byte[]{97, 98, 99});

        entityR2dbcRepository.save(entity).block();

        var actual = entityR2dbcRepository.findAll().collectList().block();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }


    @Test
    public void testJdbcFindProjection() {
        var entity = new Entity(UUID.randomUUID(), new byte[]{97, 98, 99});

        entityJdbcRepository.save(entity).block();

        var actual = entityJdbcRepository.findAllById(entity.getId()).collectList().block();

        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }




    @Override
    public @NotNull Map<String, String> getProperties() {

        var host = POSTGRE_SQL_CONTAINER.getHost();
        var port = POSTGRE_SQL_CONTAINER.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT);
        var databaseName = POSTGRE_SQL_CONTAINER.getDatabaseName();
        var stringStringMap = Map.of(
                "r2dbc.datasources.default.url", "r2dbc:postgresql://" + host + ":" + port + "/" + databaseName,
                "r2dbc.datasources.default.username", "test",
                "r2dbc.datasources.default.password", "test",
                "datasources.default.url", POSTGRE_SQL_CONTAINER.getJdbcUrl(),
                "datasources.default.username", "test",
                "datasources.default.password", "test"
        );
        System.out.println(stringStringMap);
        return stringStringMap;
    }
}
