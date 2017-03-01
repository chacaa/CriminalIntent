package com.xmartlabs.scasas.criminalintent.ui.crime.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.annimon.stream.Optional;
import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.model.Crime;
import com.xmartlabs.scasas.criminalintent.controller.CrimeController;
import com.xmartlabs.scasas.criminalintent.ui.crime.simple.Henson;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;
import rx.Observer;
import timber.log.Timber;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeListFragment extends Fragment {
  private static final String SAVED_SUBTITLE_VISIBLE = "subtitle";

  @BindView(R.id.crime_recycler_view)
  RecyclerView crimeRecyclerView;

  private CrimeAdapter adapter;
  private boolean subtitleVisible;
  @Getter
  @NonNull
  private List<Crime> crimes = Collections.emptyList();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
    ButterKnife.bind(this, view);
    setHasOptionsMenu(true);
    setpuRecyclerView();
    fetchCrimes();
    Optional.ofNullable(savedInstanceState)
        .ifPresent(b -> subtitleVisible = b.getBoolean(SAVED_SUBTITLE_VISIBLE));

    return view;
  }

  private void setpuRecyclerView() {
    crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    crimeRecyclerView.setHasFixedSize(true);
    adapter = new CrimeAdapter(this::onCrimeTapped);
    crimeRecyclerView.setAdapter(adapter);
  }

  @Override
  public void onResume() {
    super.onResume();
    updateUI();
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.fragments_crime_list, menu);
    MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
    subtitleItem.setTitle(subtitleVisible ? R.string.hide_subtitle : R.string.show_subtitle);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.menu_item_new_crime:
        startActivity(getCrimePagerActivityIntent());
        return true;
      case R.id.menu_item_show_subtitle:
        subtitleVisible = !subtitleVisible;
        getActivity().invalidateOptionsMenu();
        updateSubtitile();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private Intent getCrimePagerActivityIntent() {
    return getCrimePagerActivityIntent(null, null);
  }

  private Intent getCrimePagerActivityIntent(@Nullable Crime crime, @Nullable List<Crime> crimes) {
    return Henson.with(getActivity())
        .gotoCrimePagerActivity()
        .crime(crime)
        .crimes(crimes)
        .build();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putBoolean(SAVED_SUBTITLE_VISIBLE, subtitleVisible);
  }

  private void updateSubtitile() {
    int crimeCount = crimes.size();
    String subtitle = getString(R.string.subtitle_format, Integer.toString(crimeCount));
    AppCompatActivity activity = (AppCompatActivity) getActivity();
    activity.getSupportActionBar().setSubtitle(subtitleVisible ? subtitle : null);
  }

  private void updateUI() {
    fetchCrimes();
    updateSubtitile();
  }

  private void onCrimeTapped(Crime crime) {
    startActivity(getCrimePagerActivityIntent(crime, crimes));
  }

  public void fetchCrimes() {
    CrimeController.getInstance()
        .getCrimes()
        .subscribe(new Observer<List<Crime>>() {
          @Override
          public void onCompleted() {
          }

          @Override
          public void onError(Throwable error) {
            Timber.e(error.toString());
          }

          @Override
          public void onNext(List<Crime> crimesList) {
            isAdded();
            crimes = crimesList;
            adapter.setCrimes(crimes);
          }
        });
  }
}
