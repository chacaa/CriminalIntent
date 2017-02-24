package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import lombok.Getter;

/**
 * Created by scasas on 2/15/17.
 */
public class CrimePagerAdapter extends FragmentStatePagerAdapter {
  @Getter
  @Nullable
  private List<Crime> crimes;

  public CrimePagerAdapter(FragmentManager fragmentManager) {
    super(fragmentManager);
  }

  @MainThread
  public void setCrimes(List<Crime> crimes){
    this.crimes = crimes;
    notifyDataSetChanged();
  }

  @Override
  public Fragment getItem(int position) {
    Crime crime = crimes.get(position);
    return new CrimeFragmentBuilder()
        .crime(crime)
        .build();
  }

  @Override
  public int getCount() {
    return crimes == null ? 0 :crimes.size();
  }
}
