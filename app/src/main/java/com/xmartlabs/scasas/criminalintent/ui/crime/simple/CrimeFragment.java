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
import android.widget.Toast;

import com.annimon.stream.Optional;
import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.hannesdorfmann.fragmentargs.bundler.ParcelerArgsBundler;
import com.xmartlabs.scasas.criminalintent.CriminalIntentApplication;
import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.controller.CrimeController;
import com.xmartlabs.scasas.criminalintent.model.Crime;
import com.xmartlabs.scasas.criminalintent.ui.DatePickerFragment;
import com.xmartlabs.scasas.criminalintent.ui.DatePickerFragmentBuilder;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import rx.SingleSubscriber;
import timber.log.Timber;

@FragmentWithArgs
public class CrimeFragment extends Fragment {
  private static final String DIALOG_DATE = "dialog_date";
  private static final int REQUEST_DATE = 0;

  @Arg(bundler = ParcelerArgsBundler.class, required = false)
  Crime crime;
  @Arg(bundler = ParcelerArgsBundler.class)
  Boolean isNewCrime;

  @BindView(R.id.crime_solved)
  CheckBox crime_solved;
  @BindView(R.id.crime_title)
  EditText crime_title;
  @BindView(R.id.crime_date)
  Button dateButton;
  @BindView(R.id.save_button)
  Button saveButton;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    ButterKnife.bind(this, view);
    FragmentArgs.inject(this);
    setValues();
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
    DatePickerFragment dialog = new DatePickerFragmentBuilder(crime.getDate()).build();
    dialog.setTargetFragment(this, REQUEST_DATE);
    dialog.show(manager, DIALOG_DATE);
  }

  @OnClick(R.id.save_button)
  void onSaveButtonClicked(View view) {
    if (isNewCrime) {
      insertCrime();
    } else {
      updateCrime();
    }
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

  public void updateCrime() {
    CrimeController.getInstance().updateCrimeOnService(crime.getId(), crime)
        .subscribe(new SingleSubscriber<Crime>() {
          @Override
          public void onSuccess(Crime crime) {
            CrimeController.getInstance().updateCrime(crime);
            Toast.makeText(CriminalIntentApplication.getContext(), "Crime succesfully updated.", Toast.LENGTH_SHORT).show();
          }

          @Override
          public void onError(Throwable error) {
            Toast.makeText(CriminalIntentApplication.getContext(), "Failed updating the crime.", Toast.LENGTH_SHORT).show();
            Timber.e(error.toString());
          }
        });
  }

  public void insertCrime() {
    CrimeController.getInstance().insertCrimeOnService(crime).subscribe(new SingleSubscriber<Crime>() {
      @Override
      public void onSuccess(Crime crime) {
        CrimeController.getInstance().insertCrime(crime);
        Toast.makeText(CriminalIntentApplication.getContext(), "Crime succesfully inserted.", Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onError(Throwable error) {
        Toast.makeText(CriminalIntentApplication.getContext(), "Failed inserting the crime.", Toast.LENGTH_SHORT).show();
        Timber.e(error.toString());
      }
    });
  }
}
