package com.xmartlabs.scasas.criminalintent.ui.crime.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.model.Crime;
import com.xmartlabs.scasas.criminalintent.model.CrimeController;
import com.xmartlabs.scasas.criminalintent.ui.crime.simple.CrimeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeListFragment extends Fragment {
  @BindView(R.id.crime_recycler_view)
  RecyclerView crimeRecyclerView;

  private CrimeAdapter adapter;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
    ButterKnife.bind(this, view);
    setpuRecyclerView();

    return view;
  }

  private void setpuRecyclerView() {
    crimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    adapter = new CrimeAdapter(this::onCrimeTapped);
    crimeRecyclerView.setAdapter(adapter);
  }

  @Override
  public void onResume() {
    super.onResume();
    updateUI();
  }

  private void updateUI() {
    CrimeController crimelab = CrimeController.getInstance();
    List<Crime> crimes = crimelab.getCrimes();
    adapter.setCrimes(crimes);
  }

  private void onCrimeTapped(Crime crime) {
    Intent intent = CrimeActivity.newIntent(getActivity(), crime.getId());
    startActivity(intent);
  }
}
