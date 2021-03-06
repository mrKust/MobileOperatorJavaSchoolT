package com.school.customSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.school.database.entity.Options;
import com.school.database.entity.Tariff;

import java.io.IOException;

/**
 * This class serialize Options entity to json format
 */
public class CustomTariffSerializer extends StdSerializer<Tariff> {

    public CustomTariffSerializer() {
        this(null);
    }
    public CustomTariffSerializer(Class<Tariff> t) {
        super(t);
    }

    /**
     * Methods convert tariff entity to json format
     * @param tariff entity which will be converted
     * @param jsonGenerator convert string, numbers and object in json format
     * @param serializerProvider provider
     * @throws IOException If generator couldn't write something
     */
    @Override
    public void serialize(Tariff tariff, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("tariff_name", tariff.getTariffName());
        jsonGenerator.writeNumberField("tariff_price", tariff.getPrice());
        jsonGenerator.writeFieldName("connectedOptions");
        jsonGenerator.writeStartArray();
        for (Options o : tariff.getOptions()) {
            if (o.isAvailableOptionToConnectOrNot())
                jsonGenerator.writeObject(o);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }


}