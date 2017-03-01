package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Getter;

/**
 * Created by scasas on 2/15/17.
 */
public class CrimePagerAdapter extends FragmentStatePagerAdapter {
  @Getter
  @Nullable
  private List<Crime> crimes;
  private boolean isNewCrime;

  public CrimePagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @MainThread
  public void setCrimes(List<Crime> crimes, boolean isNewCrime) {
    this.crimes = crimes;
    this.isNewCrime = isNewCrime;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    Crime crime = crimes.get(position);
    return new CrimeFragmentBuilder(isNewCrime)
        .crime(crime)
        .build();
  }

  @Override
  public int getCount() {
    return crimes == null ? 0 : crimes.size();
  }
}
