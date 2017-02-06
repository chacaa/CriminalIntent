package com.xmartlabs.scasas.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.BindView;

public class CrimeFragment extends Fragment {
  @BindView(R.id.cime_title)
  EditText titleField;

  private Crime crime = new Crime();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_crime, container, false);
    setupViewListeners();
    return view;
  }

  private void setupViewListeners() {
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
}
