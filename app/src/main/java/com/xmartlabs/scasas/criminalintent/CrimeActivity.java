package com.xmartlabs.scasas.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

/**
 * Created by scasas on 2/2/17.
 */
public class CrimeActivity extends FragmentActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_crime);
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
    if (fragment == null) {
      fragment = new CrimeFragment();
      fragmentManager
          .beginTransaction()
          .add(R.id.fragment_container, fragment)
          .commit();
    }
  }
}
