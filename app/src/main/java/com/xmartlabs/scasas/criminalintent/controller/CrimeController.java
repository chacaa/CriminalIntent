package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

import com.annimon.stream.Stream;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import com.raizlabs.android.dbflow.structure.BaseModel;
import com.xmartlabs.scasas.criminalintent.crimeservice.ServiceHelper;
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

  public Single<List<Crime>> getCrimesFromDataBase() {
    return Single.fromCallable(() -> SQLite.select()
        .from(Crime.class)
        .where()
        .queryList())
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Crime>> getCrimesFromService() {
    return ServiceHelper.SERVICE.getCrimes()
        .observeOn(Schedulers.io())
        .doOnSuccess(crimes -> {
          SQLite.delete().from(Crime.class).execute();
          Stream.of(crimes).forEach(BaseModel::insert);
        })
        .subscribeOn(Schedulers.io());
  }

  public Single<Crime> insertCrime(Crime crime) {
    return ServiceHelper.SERVICE.createCrime(crime)
        .doOnSuccess(BaseModel::insert)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Single<Crime> updateCrime(String id, Crime crime) {
    return ServiceHelper.SERVICE.updateCrime(id, crime)
        .doOnSuccess(BaseModel::update)
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
