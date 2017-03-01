package com.xmartlabs.scasas.criminalintent.module;

import android.content.Context;

import com.google.gson.Gson;
import com.xmartlabs.scasas.criminalintent.crimeservice.CrimeService;
import com.xmartlabs.scasas.criminalintent.R;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by scasas on 2/23/17.
 */
@Module
public class RestServiceModule {
  @Provides
  @Singleton
  Retrofit provideRetrofit(Context context, OkHttpClient client,
                           RxJavaCallAdapterFactory rxJavaCallAdapterFactory,
                           GsonConverterFactory gsonConverterFactory) {
    return new Retrofit.Builder()
        .addCallAdapterFactory(rxJavaCallAdapterFactory)
        .addConverterFactory(gsonConverterFactory)
        .baseUrl(getBaseUrl(context))
        .client(client)
        .build();
  }

  @Provides
  @Singleton
  RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
    return RxJavaCallAdapterFactory.create();
  }

  @Provides
  @Singleton
  GsonConverterFactory provideGsonConverterFactory(Gson gson) {
    return GsonConverterFactory.create(gson);
  }

  @Provides
  @Singleton
  CrimeService provideCrimeService(Retrofit retrofit) {
    return retrofit.create(CrimeService.class);
  }

  protected String getBaseUrl(Context context) {
    return context.getString(R.string.url_service);
  }
}
