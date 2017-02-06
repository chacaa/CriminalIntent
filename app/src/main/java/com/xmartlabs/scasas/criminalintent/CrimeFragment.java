package com.xmartlabs.scasas.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTextChanged;

public class CrimeFragment extends Fragment {
  @BindView(R.id.crime_title)
  EditText titleField;

  private Crime crime = new Crime();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @OnTextChanged(R.id.crime_title)
  void onTitleTextChange(CharSequence newTitle) {
    crime.setTitle(newTitle.toString());
  }
}
