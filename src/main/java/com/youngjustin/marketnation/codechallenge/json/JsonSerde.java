package com.youngjustin.marketnation.codechallenge.json;

public interface JsonSerde {

  String serialize(Object obj) throws JsonSerializationException;

  Object deserialize(final String str, Class clazz) throws JsonDeserializationException;
}
