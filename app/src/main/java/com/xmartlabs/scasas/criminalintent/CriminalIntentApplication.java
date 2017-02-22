package com.xmartlabs.scasas.criminalintent;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by scasas on 2/21/17.
 */

public class CriminalIntentApplication extends Application {
  @Override
  public void onCreate() {
    super.onCreate();
    initializeDataBase();
  }

  private void initializeDataBase() {
    FlowManager.init(new FlowConfig.Builder(this).build());
  }
}
