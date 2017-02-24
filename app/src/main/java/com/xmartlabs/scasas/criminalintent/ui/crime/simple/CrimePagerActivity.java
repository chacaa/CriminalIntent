package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.annimon.stream.Objects;
import com.annimon.stream.Stream;

import com.f2prateek.dart.Dart;
import com.f2prateek.dart.InjectExtra;

import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.controller.CrimeController;
import com.xmartlabs.scasas.criminalintent.model.Crime;
import com.xmartlabs.scasas.criminalintent.ui.crime.list.CrimeListFragment;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 2/14/17.
 */
public class CrimePagerActivity extends AppCompatActivity {
  @InjectExtra
  Crime crime;

  @InjectExtra
  List<Crime> crimes;

  @BindView(R.id.activity_crime_pager_view_pager)
  ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupLayout();
    Dart.inject(this);
    setupCrimePagerAdapter();
    setCurrentItem(crime.getId());
  }

  private void setCurrentItem(String crimeId) {
    Stream.range(0, crimes.size())
        .filter(index -> Objects.equals(crimes.get(index).getId(), crimeId))
        .findFirst()
        .ifPresent(viewPager::setCurrentItem);
  }

  private void setupLayout() {
    setContentView(R.layout.activity_crime_pager);
    ButterKnife.bind(this);
  }

  private void setupCrimePagerAdapter() {
    FragmentManager fragmentManager = getSupportFragmentManager();
    CrimePagerAdapter crimePagerAdapter = new CrimePagerAdapter(fragmentManager);
    crimePagerAdapter.setCrimes(crimes);
    viewPager.setAdapter(crimePagerAdapter);
  }

//  private List<Crime> getAllCrimes() {
////    return CrimeController.getCrimes();
//    return crimePagerAdapter.getCrimes();
//  }
}
