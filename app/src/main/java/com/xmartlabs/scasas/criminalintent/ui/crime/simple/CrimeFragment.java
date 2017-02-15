package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import android.widget.CheckBox;

import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.model.Crime;
import com.xmartlabs.scasas.criminalintent.controller.CrimeController;

import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnTextChanged;

public class CrimeFragment extends Fragment {
  private static final String CRIME_ID = "crime_id";

  @BindView(R.id.crime_solved)
  CheckBox crime_solved;
  @BindView(R.id.crime_title)
  EditText crime_title;
  @BindView(R.id.crime_date)
  Button dateButton;

  private Crime crime;

  public static CrimeFragment newInstance(UUID crimeId) {
    Bundle arguments = new Bundle();
    arguments.putSerializable(CRIME_ID, crimeId);
    return setupFragment(arguments);
  }

  static CrimeFragment setupFragment(Bundle arguments) {
    CrimeFragment crimeFragment = new CrimeFragment();
    crimeFragment.setArguments(arguments);
    return crimeFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    UUID crimeId = (UUID) getArguments().getSerializable(CRIME_ID);
    ButterKnife.bind(this, view);
    if (crimeId != null) {
      crime = CrimeController.getInstance().getCrime(crimeId);
      setValues();
    } else {
      //TODO it will be used later
      crime = new Crime.Builder()
          .date(new Date())
          .solved(false)
          .title("")
          .build();
      setupDateButton();
    }
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

  private void setValues() {
    crime_solved.setChecked(crime.isSolved());
    crime_title.setText(crime.getTitle());
    setupDateButton();
  }
}
