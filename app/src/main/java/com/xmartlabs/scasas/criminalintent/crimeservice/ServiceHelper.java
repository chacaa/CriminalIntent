package com.xmartlabs.scasas.criminalintent.crimeservice;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moczul.ok2curl.CurlInterceptor;
import com.xmartlabs.scasas.criminalintent.BuildConfig;
import com.xmartlabs.scasas.criminalintent.application.CriminalIntentApplication;
import com.xmartlabs.scasas.criminalintent.crimeservice.serviceutils.MilisecondsDateAdapter;
import com.xmartlabs.scasas.criminalintent.R;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

/**
 * Created by scasas on 2/23/17.
 */
public class ServiceHelper {
  public static final Gson GSON = provideGson();
  public static final OkHttpClient OK_HTTP_CLIENT = provideServiceOkHttpClient();
  public static final Retrofit RETROFIT = provideRetrofit();
  public static final CrimeService SERVICE = provideService();

  private static GsonBuilder provideCommonGsonBuilder() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();
    return gsonBuilder;
  }

  private static Gson provideGson() {
    return provideCommonGsonBuilder()
        .registerTypeAdapter(Date.class, new MilisecondsDateAdapter())
        .create();
  }

  private static OkHttpClient.Builder provideOkHttpClientBuilder() {
    return new OkHttpClient.Builder();
  }

  private static OkHttpClient provideServiceOkHttpClient() {
    OkHttpClient.Builder clientBuilder = provideOkHttpClientBuilder();
    addLoggingInterceptor(clientBuilder);
    return clientBuilder.build();
  }

  private static Retrofit provideRetrofit() {
    return new Retrofit.Builder()
        .addCallAdapterFactory(provideRxJavaCallAdapterFactory())
        .addConverterFactory(provideGsonConverterFactory())
        .baseUrl(getBaseUrl(CriminalIntentApplication.getContext()))
        .client(OK_HTTP_CLIENT)
        .build();
  }

  private static RxJavaCallAdapterFactory provideRxJavaCallAdapterFactory() {
    return RxJavaCallAdapterFactory.create();
  }

  private static GsonConverterFactory provideGsonConverterFactory() {
    return GsonConverterFactory.create(GSON);
  }

  private  static String getBaseUrl(Context context) {
    return context.getString(R.string.url_service);
  }

  private static CrimeService provideService() {
    return RETROFIT.create(CrimeService.class);
  }

  private static void addLoggingInterceptor(@NonNull OkHttpClient.Builder clientBuilder) {
    if (BuildConfig.DEBUG) {
      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").d(message));
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      clientBuilder.addInterceptor(loggingInterceptor);

      CurlInterceptor curlInterceptor = new CurlInterceptor();
      clientBuilder.addInterceptor(curlInterceptor);
    }
  }
}
