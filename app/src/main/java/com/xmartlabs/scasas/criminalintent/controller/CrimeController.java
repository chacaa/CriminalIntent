package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import com.xmartlabs.scasas.criminalintent.ServiceHelper;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

  public Single<List<Crime>> getCrimesFromDataBase() {
    return Single.fromCallable(() -> SQLite.select()
        .from(Crime.class)
        .where()
        .queryList());
  }

  public Single<List<Crime>> getCrimesFromService() {
    return ServiceHelper.SERVICE.getCrimes()
        .observeOn(Schedulers.io())
        .doOnSuccess(crimes -> {
          SQLite.delete().from(Crime.class).execute();
          Stream.of(crimes).forEach(this::insertCrime);
        })
        .subscribeOn(Schedulers.io());
  }

  public Single<Crime> insertCrimeOnService(Crime crime) {
    return ServiceHelper.SERVICE.createCrime(crime)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Single<Crime> updateCrimeOnService(String id, Crime crime) {
    return ServiceHelper.SERVICE.updateCrime(id, crime)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Observable<List<Crime>> getCrimes() {
    return Observable.concatDelayError(
        getCrimesFromDataBase().toObservable(),
        getCrimesFromService().toObservable()
    ).observeOn(AndroidSchedulers.mainThread());
  }
}
