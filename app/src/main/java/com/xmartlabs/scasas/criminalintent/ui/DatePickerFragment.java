package com.xmartlabs.scasas.criminalintent.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.hannesdorfmann.fragmentargs.FragmentArgs;
import com.hannesdorfmann.fragmentargs.annotation.Arg;
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs;
import com.xmartlabs.scasas.criminalintent.R;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 2/16/17.
 */
@FragmentWithArgs
public class DatePickerFragment extends DialogFragment {
  public static final String EXTRA_DATE = DatePickerFragment.class.getCanonicalName() + ".date";

  @Arg
  Date date;

  @BindView(R.id.dialog_date_picker)
  DatePicker datePicker;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    View view = inflateView();
    ButterKnife.bind(this, view);
    FragmentArgs.inject(this);
    setupDatePicker();
    return new AlertDialog.Builder(getActivity())
        .setView(view)
        .setTitle(R.string.date_picker_title)
        .setNegativeButton(android.R.string.cancel, null)
        .setPositiveButton(android.R.string.ok, onClickAction())
        .create();
  }

  private View inflateView() {
    return LayoutInflater.from(getActivity())
        .inflate(R.layout.dialog_date, null);
  }

  private void setupDatePicker() {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    datePicker.init(year, month, day, null);
  }

  private void sendResult(int resultCode, Date date) {
    if (getTargetFragment() == null) {
      return;
    }
    getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, setupIntent(date));
  }

  private Intent setupIntent(Date date) {
    return new Intent().putExtra(EXTRA_DATE, date);
  }

  private DialogInterface.OnClickListener onClickAction() {
    return (dialog, which) -> sendResult(Activity.RESULT_OK, newDate());
  }

  private Date newDate() {
    int year = datePicker.getYear();
    int month = datePicker.getMonth();
    int day = datePicker.getDayOfMonth();
    return new GregorianCalendar(year, month, day).getTime();
  }
}
