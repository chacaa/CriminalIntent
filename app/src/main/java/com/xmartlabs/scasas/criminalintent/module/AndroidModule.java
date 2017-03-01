package com.xmartlabs.scasas.criminalintent.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.xmartlabs.scasas.criminalintent.application.CriminalIntentApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scasas on 3/1/17.
 */
@Module
public class AndroidModule {
  private final CriminalIntentApplication criminalIntentApplication;

  public AndroidModule(CriminalIntentApplication criminalIntentApplication) {
    this.criminalIntentApplication = criminalIntentApplication;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return criminalIntentApplication;
  }

  @Provides
  @Singleton
  SharedPreferences provideSharedPreferences() {
    return PreferenceManager.getDefaultSharedPreferences(criminalIntentApplication);
  }
}
