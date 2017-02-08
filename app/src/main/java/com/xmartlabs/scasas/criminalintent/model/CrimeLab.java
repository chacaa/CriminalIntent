package com.xmartlabs.scasas.criminalintent.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeLab {
  private static CrimeLab crimeLab;

  private List<Crime> crimes;

  public List<Crime> getCrimes() {
    return crimes;
  }

  public static CrimeLab get(Context context) {
    if (crimeLab == null) {
      crimeLab = new CrimeLab(context);
    }
    return crimeLab;
  }

  private CrimeLab(Context context) {
    crimes = new ArrayList<>();
    generateTestCrimes(100);
  }

  public Crime getCrime(UUID id) {
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
