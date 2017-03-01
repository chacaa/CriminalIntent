package com.xmartlabs.scasas.criminalintent.module;

import com.xmartlabs.scasas.criminalintent.controller.CrimeController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by scasas on 3/1/17.
 */
@Module
public class ControllerModule {
  @Provides
  @Singleton
  CrimeController provideCrimeController() {
    return new CrimeController();
  }
}
