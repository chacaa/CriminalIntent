package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.annimon.stream.Objects;
import com.annimon.stream.Stream;
import com.xmartlabs.scasas.criminalintent.model.Crime;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeController {
  private final static CrimeController INSTANCE = new CrimeController();

  private final List<Crime> crimes = new ArrayList<>();

  public List<Crime> getCrimes() {
    return crimes;
  }

  public static CrimeController getInstance() {
    return INSTANCE;
  }

//  private CrimeController() {
//  }

  public Crime getCrime(@NonNull UUID id) {
    return Stream.of(crimes)
        .filter(crime -> Objects.equals(crime.getId(), id))
        .findFirst()
        .orElse(null);
  }

  public void addCrime(Crime crime) {
    crimes.add(crime);
  }

  private void generateTestCrimes(int numberOfCrimes) {
    Stream.range(0, numberOfCrimes - 1)
        .map(index -> new Crime.Builder()
            .title("Crime #" + index)
            .solved(index % 11 == 0 || index % 21 == 0)
            .date(new Date())
            .build())
        .forEach(crimes::add);
  }
}
