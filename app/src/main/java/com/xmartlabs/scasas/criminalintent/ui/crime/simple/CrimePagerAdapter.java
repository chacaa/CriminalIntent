package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

/**
 * Created by scasas on 2/15/17.
 */
public class CrimePagerAdapter extends FragmentStatePagerAdapter {
  private final List<Crime> crimes;

  public CrimePagerAdapter(FragmentManager fragmentManager, List<Crime> crimes) {
    super(fragmentManager);
    this.crimes = crimes;
  }

  @Override
  public Fragment getItem(int position) {
    Crime crime = crimes.get(position);
    return CrimeFragment.newInstance(crime.getId());
  }

  @Override
  public int getCount() {
    return crimes.size();
  }
}
