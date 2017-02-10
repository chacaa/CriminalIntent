package com.xmartlabs.scasas.criminalintent.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

  public Crime getCrime(UUID id) {
    //TODO it would be used later
    return Stream.of(crimes)
        .filter(crime -> crime.getId() == id)
        .findFirst()
        .orElse(null);
  }

  private void generateTestCrimes(int numberOfCrimes) {
    Stream.range(0, numberOfCrimes - 1)
        .map(integer -> {
          Crime crime = new Crime();
          crime.setTitle("Crime #" + integer);
          crime.setSolved(integer % 11 == 0 || integer % 21 == 0);
          return crime;
        })
        .forEach(crimes::add);
  }
}
