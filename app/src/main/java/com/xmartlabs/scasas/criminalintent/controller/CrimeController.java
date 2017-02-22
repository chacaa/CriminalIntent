package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.xmartlabs.scasas.criminalintent.model.Crime;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeController {
  @NonNull
  private final static CrimeController INSTANCE = new CrimeController();

  public static CrimeController getInstance() {
    return INSTANCE;
  }

  public void insertCrime(Crime crime) {
    crime.insert();
  }

  public void updateCrime(Crime crime) {
    crime.update();
  }

  public static List<Crime> getCrimes(@NonNull SQLCondition... sqlConditions) {
    return SQLite.select()
        .from(Crime.class)
        .where(sqlConditions)
        .queryList();
  }
}
