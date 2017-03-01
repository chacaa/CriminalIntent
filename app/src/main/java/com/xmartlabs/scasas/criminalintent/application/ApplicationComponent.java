package com.xmartlabs.scasas.criminalintent.application;

import com.xmartlabs.scasas.criminalintent.controller.CrimeController;
import com.xmartlabs.scasas.criminalintent.module.AndroidModule;
import com.xmartlabs.scasas.criminalintent.module.ControllerModule;
import com.xmartlabs.scasas.criminalintent.module.GsonModule;
import com.xmartlabs.scasas.criminalintent.module.OkHttpModule;
import com.xmartlabs.scasas.criminalintent.module.RestServiceModule;
import com.xmartlabs.scasas.criminalintent.ui.crime.list.CrimeListFragment;
import com.xmartlabs.scasas.criminalintent.ui.crime.simple.CrimeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by scasas on 3/1/17.
 */
@Singleton
@Component(modules = {
    AndroidModule.class,
    ControllerModule.class,
    GsonModule.class,
    OkHttpModule.class,
    RestServiceModule.class
})
public interface ApplicationComponent {
  void inject(CrimeController crimeController);
  void inject(CrimeFragment crimeFragment);
  void inject(CrimeListFragment crimeListFragment);
}
