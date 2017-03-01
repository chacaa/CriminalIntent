package com.xmartlabs.scasas.criminalintent.application;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by scasas on 2/21/17.
 */
public class CriminalIntentApplication extends Application {
  private static CriminalIntentApplication instance;

  public CriminalIntentApplication() {
    instance = this;
  }

  public static CriminalIntentApplication getContext() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initializeDataBase();
  }

  private void initializeDataBase() {
    FlowManager.init(new FlowConfig.Builder(this).build());
  }
}
