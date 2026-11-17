package com.example.desafio_dio.infra.jackson.deser;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.deser.std.StdDeserializer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersion = -4589164298310392227L;

    protected LocalDateDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws JacksonException {
        JsonParser parser = null;
        return LocalDate.parse(
                parser.readValueAs(String.class),
                DateTimeFormatter.ofPattern("dd/MM/yyyy")
        );
    }
}
