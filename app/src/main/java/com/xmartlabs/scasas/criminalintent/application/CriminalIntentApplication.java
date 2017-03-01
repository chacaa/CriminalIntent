package com.xmartlabs.scasas.criminalintent.application;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.xmartlabs.scasas.criminalintent.module.AndroidModule;

import bullet.ObjectGraph;

/**
 * Created by scasas on 2/21/17.
 */
public class CriminalIntentApplication extends Application {
  private static CriminalIntentApplication instance;

  private ObjectGraph bullet;

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
    initializeInjections();
  }

  private void initializeDataBase() {
    FlowManager.init(new FlowConfig.Builder(this).build());
  }

  private void initializeInjections() {
    ApplicationComponent component = createComponent();
    bullet = createBullet(component);
  }

  protected ApplicationComponent createComponent() {
    return DaggerApplicationComponent.builder()
        .androidModule(new AndroidModule(this))
        .build();
  }

  protected ObjectGraph createBullet(ApplicationComponent component) {
    return new BulletApplicationComponent(component);
  }

  public <T> T inject(final T t) {
    return bullet.inject(t);
  }
}
