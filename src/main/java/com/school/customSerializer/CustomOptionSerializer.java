package com.school.customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.school.database.entity.Options;

import java.io.IOException;

/**
 * This class serialize Options entity to json format
 */
public class CustomOptionSerializer extends StdSerializer<Options> {

    public CustomOptionSerializer() {
        this(null);
    }
    public CustomOptionSerializer(Class<Options> t) {
        super(t);
    }

    /**
     * Method convert option entity to json string
     * @param options entity which will be converted
     * @param jsonGenerator convert string, numbers and object in json format
     * @param serializerProvider provider
     * @throws IOException If generator couldn't write something
     */
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
