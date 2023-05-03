package com.youngjustin.marketnation.codechallenge.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonJsonSerde implements JsonSerde {

    private ObjectMapper objectMapper;

    public JacksonJsonSerde() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String serialize(Object obj) throws JsonSerializationException {
        try {
            return this.objectMapper.writeValueAsString(obj);
        }
        catch (JsonProcessingException ex) {
            throw new JsonSerializationException(ex);
        }
    }

    @Override
    public Object deserialize(String str, Class clazz) throws JsonDeserializationException {
        try {
            return this.objectMapper.readValue(str, clazz);
        }
        catch (JsonProcessingException ex) {
            throw new JsonDeserializationException(ex);
        }
    }
}
