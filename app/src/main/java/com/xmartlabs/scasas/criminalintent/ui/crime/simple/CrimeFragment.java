package com.xmartlabs.scasas.criminalintent.ui.crime.simple;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.annimon.stream.Optional;
import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.controller.CrimeController;
import com.xmartlabs.scasas.criminalintent.model.Crime;
import com.xmartlabs.scasas.criminalintent.ui.DatePickerFragment;

import java.util.Date;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class CrimeFragment extends Fragment {
  private static final String CRIME_ID = "crime_id";
  private static final String DIALOG_DATE = "dialog_date";
  private static final int REQUEST_DATE = 0;

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

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode != Activity.RESULT_OK) {
      return;
    }
    if (requestCode == REQUEST_DATE) {
      Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
      crime.setDate(date);
      setupDateButton();
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }
  }

  @OnTextChanged(R.id.crime_title)
  void onTitleTextChanged(CharSequence newTitle) {
    crime.setTitle(newTitle.toString());
  }

  @OnCheckedChanged(R.id.crime_solved)
  void onSolvedCheckboxChanged(boolean isChecked) {
    crime.setSolved(isChecked);
    displayNotification(isChecked ? R.string.checked_solved : R.string.unchecked_solved);
  }

  @OnClick(R.id.crime_date)
  void onDateButtonClicked(View view) {
    FragmentManager manager = getFragmentManager();
    DatePickerFragment dialog = DatePickerFragment.newInstance(crime.getDate());
    dialog.setTargetFragment(this, REQUEST_DATE);
    dialog.show(manager, DIALOG_DATE);
  }

  void setupDateButton() {
    dateButton.setText(crime.getDate().toString());
  }

  private void setValues() {
    crime_solved.setChecked(crime.isSolved());
    crime_title.setText(crime.getTitle());
    setupDateButton();
  }

  private void displayNotification(int message) {
    Optional.ofNullable(getView())
        .ifPresent(view -> Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show());
  }
}
