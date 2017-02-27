package com.xmartlabs.scasas.criminalintent;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by scasas on 2/24/17.
 */
public class MilisecondsDateAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

  @Override
  public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    return new Date(Long.valueOf(json.getAsJsonPrimitive().getAsString()));
  }

  @Override
  public JsonElement serialize(Date date, Type typeOfSrc, JsonSerializationContext context) {
    return new JsonPrimitive(date.getTime());
  }
}