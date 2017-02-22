package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

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

  public void insertCrime(@NonNull Crime crime) {
    crime.insert();
  }

  public void updateCrime(@NonNull Crime crime) {
    crime.update();
  }

  public static List<Crime> getCrimes(@NonNull SQLCondition... sqlConditions) {
    return SQLite.select()
        .from(Crime.class)
        .where(sqlConditions)
        .queryList();
  }
}
