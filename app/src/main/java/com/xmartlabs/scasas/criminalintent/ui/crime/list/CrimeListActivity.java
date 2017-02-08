package com.xmartlabs.scasas.criminalintent.ui.crime.list;

import android.support.v4.app.Fragment;

import com.xmartlabs.scasas.criminalintent.ui.common.SingleFragmentActivity;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeListActivity extends SingleFragmentActivity {
  @Override
  protected Fragment createFragment() {
    return new CrimeListFragment();
  }
}
