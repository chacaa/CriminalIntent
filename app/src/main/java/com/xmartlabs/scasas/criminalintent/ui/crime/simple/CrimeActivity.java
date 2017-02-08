package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.support.v4.app.Fragment;

import com.xmartlabs.scasas.criminalintent.ui.common.SingleFragmentActivity;

/**
 * Created by scasas on 2/2/17.
 */
public class CrimeActivity extends SingleFragmentActivity {
  @Override
  protected Fragment createFragment() {
    return new CrimeFragment();
  }
}

