package com.xmartlabs.scasas.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    //TODO it would be use later
    for (Crime crime : crimes) {
      if (crime.getId().equals(id)) {
        return crime;
      }
    }
    return null;
  }

  private void generateTestCrimes(int numberOfCrimes) {
    for (int i = 0; i < numberOfCrimes; i++) {
      Crime crime = new Crime();
      crime.setTitle("Crime #" + i);
      crime.setSolved(i % 11 == 0 || i % 21 == 0);
      crimes.add(crime);
    }
  }
}
