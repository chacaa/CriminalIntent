package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.raizlabs.android.dbflow.sql.language.SQLCondition;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.xmartlabs.scasas.criminalintent.Service;
import com.xmartlabs.scasas.criminalintent.ServiceHelper;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeController {
  private static Service service;

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

//  public static List<Crime> getCrimes(@NonNull SQLCondition... sqlConditions) {
//    return SQLite.select()
//        .from(Crime.class)
//        .where(sqlConditions)
//        .queryList();
//  }

  public static Single<List<Crime>> getCrimesFromService(){
    return ServiceHelper.SERVICE.getCrimes()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

}
