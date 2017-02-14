package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.xmartlabs.scasas.criminalintent.ui.common.SingleFragmentActivity;

import java.util.UUID;

/**
 * Created by scasas on 2/2/17.
 */
public class CrimeActivity extends SingleFragmentActivity {
  private static final String EXTRA_CRIME_ID = CrimeActivity.class.getCanonicalName() + ".crime_id";

  @Override
  protected Fragment createFragment() {
    UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
    return CrimeFragment.newInstance(crimeId);
  }

  public static Intent newIntent(Context packageContext, UUID crimeId) {
    Intent intent = new Intent(packageContext, CrimeActivity.class);
    intent.putExtra(EXTRA_CRIME_ID, crimeId);
    return intent;
  }
}
