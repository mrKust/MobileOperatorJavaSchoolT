package com.school.customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.school.database.entity.Options;

import java.io.IOException;

public class CustomOptionSerializer extends StdSerializer<Options> {

    public CustomOptionSerializer() {
        this(null);
    }
    public CustomOptionSerializer(Class<Options> t) {
        super(t);
    }

    @Override
    public void serialize(Options options, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("options_name", options.getOptionsName());
        jsonGenerator.writeStringField("options_category", options.getOptionType().getOptionType());
        jsonGenerator.writeNumberField("options_price", options.getPrice());
        jsonGenerator.writeNumberField("options_costToAdd", options.getCostToAdd());
        jsonGenerator.writeEndObject();
    }
}
