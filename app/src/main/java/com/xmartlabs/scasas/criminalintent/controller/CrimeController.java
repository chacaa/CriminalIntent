package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.xmartlabs.scasas.criminalintent.model.Crime;

import lombok.Getter;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeController {
  @NonNull
  private final static CrimeController INSTANCE = new CrimeController();

  @Getter
  private final List<Crime> crimes = new ArrayList<>();

  public static CrimeController getInstance() {
    return INSTANCE;
  }

  public void addCrime(Crime crime) {
    crimes.add(crime);
  }
}
