package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.annimon.stream.Objects;
import com.annimon.stream.Stream;

import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.controller.CrimeController;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 2/14/17.
 */
public class CrimePagerActivity extends AppCompatActivity {
  private static final String EXTRA_CRIME_ID = CrimePagerActivity.class.getCanonicalName() + ".crime_id";

  @BindView(R.id.activity_crime_pager_view_pager)
  ViewPager viewPager;

  private final List<Crime> crimes = CrimeController.getINSTANCE().getCrimes();

  public static Intent newIntent(Context packageContext, UUID crimeId) {
    return new Intent(packageContext, CrimePagerActivity.class)
        .putExtra(EXTRA_CRIME_ID, crimeId);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupLayout();
    setupCrimePagerAdapter();
    UUID crimeId = obtainCrimeIdFromCall();
    setCurrentItem(crimeId);
  }

  private void setCurrentItem(UUID crimeId) {
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
    CrimePagerAdapter crimePagerAdapter = new CrimePagerAdapter(fragmentManager, crimes);
    viewPager.setAdapter(crimePagerAdapter);
  }

  private UUID obtainCrimeIdFromCall() {
    return (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
  }
}
