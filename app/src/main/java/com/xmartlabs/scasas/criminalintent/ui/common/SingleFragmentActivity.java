package com.xmartlabs.scasas.criminalintent.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.xmartlabs.scasas.criminalintent.R;

/**
 * Created by scasas on 2/7/17.
 */
public abstract class SingleFragmentActivity extends FragmentActivity {
  protected abstract Fragment createFragment();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fragment);
    FragmentManager fragmentManager = getSupportFragmentManager();
    setupFragmentManager(fragmentManager);
  }

  private void setupFragmentManager(FragmentManager fragmentManager) {
    Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
    if (fragment == null) {
      fragment = createFragment();
      fragmentManager
          .beginTransaction()
          .add(R.id.fragment_container, fragment)
          .commit();
    }
  }
}
