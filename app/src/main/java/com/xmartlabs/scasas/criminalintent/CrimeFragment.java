package com.xmartlabs.scasas.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CrimeFragment extends Fragment {
  private EditText titleField;

  private Crime crime;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    crime = new Crime();
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    bindViews(view);
    setupListeners();
    return view;
  }

  private void setupListeners() {
    titleField.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        crime.setTitle(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {
      }
    });
  }

  private void bindViews(View view) {
    titleField = (EditText) view.findViewById(R.id.cime_title);
  }
}
