package com.xmartlabs.scasas.criminalintent;

import com.annimon.stream.Objects;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Date;

import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by scasas on 2/24/17.
 */
public class ServiceStringConverter extends Converter.Factory {
  @Override
  public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
    if (type instanceof Class) {
      if (Objects.equals(type, Date.class)) {
        return value -> String.valueOf(((Date) value));//.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
      } else if (Objects.equals(type, Date.class)) {
        return value -> String.valueOf(((Date) value));//.toInstant(ZoneOffset.UTC).toEpochMilli());
      }
    }
    return super.stringConverter(type, annotations, retrofit);
  }
}
