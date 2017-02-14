package com.xmartlabs.scasas.criminalintent.model;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.annimon.stream.Objects;
import com.annimon.stream.Stream;

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

  private CrimeController() {
    generateTestCrimes(100);
  }

  public Crime getCrime(@NonNull UUID id) {
    return Stream.of(crimes)
        .filter(crime -> Objects.equals(crime.getId(), id))
        .findFirst()
        .orElse(null);
  }

  private void generateTestCrimes(int numberOfCrimes) {
    Stream.range(0, numberOfCrimes - 1)
        .map(integer -> new Crime.Builder()
            .title("Crime #" + integer)
            .solved(integer % 11 == 0 || integer % 21 == 0)
            .date(new Date())
            .build())
        .forEach(crimes::add);
  }
}
