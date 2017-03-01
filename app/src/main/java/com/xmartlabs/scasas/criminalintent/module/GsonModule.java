package com.xmartlabs.scasas.criminalintent.module;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xmartlabs.scasas.criminalintent.crimeservice.serviceutils.MilisecondsDateAdapter;

import java.util.Date;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scasas on 3/1/17.
 */
@Module
public class GsonModule {
  @NonNull
  @Provides
  GsonBuilder provideCommonGsonBuilder() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    return gsonBuilder;
  }

  @Provides
  @Singleton
  Gson provideGson(GsonBuilder gsonBuilder) {
    return gsonBuilder
        .registerTypeAdapter(Date.class, new MilisecondsDateAdapter())
        .create();
  }

}
