package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnTextChanged;

public class CrimeFragment extends Fragment {
  @BindView(R.id.crime_date)
  Button dateButton;

  private Crime crime = new Crime();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    ButterKnife.bind(this, view);
    setupDateButton();
    return view;
  }

  @OnTextChanged(R.id.crime_title)
  void onTitleTextChange(CharSequence newTitle) {
    crime.setTitle(newTitle.toString());
  }

  @OnCheckedChanged(R.id.crime_solved)
  void onSolvedCheckboxChange(boolean isChecked) {
    crime.setSolved(isChecked);
  }

  void setupDateButton() {
    dateButton.setText(crime.getDate().toString());
    dateButton.setEnabled(false);
  }
}
