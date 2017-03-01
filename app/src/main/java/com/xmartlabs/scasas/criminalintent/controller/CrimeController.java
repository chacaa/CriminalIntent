package com.xmartlabs.scasas.criminalintent.controller;

import com.annimon.stream.Stream;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import com.raizlabs.android.dbflow.structure.BaseModel;
import com.xmartlabs.scasas.criminalintent.application.CriminalIntentApplication;
import com.xmartlabs.scasas.criminalintent.crimeservice.CrimeService;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeController {
  @Inject
  CrimeService crimeService;

  public CrimeController() {
    CriminalIntentApplication.getContext().inject(this);
  }

  public Single<List<Crime>> getCrimesFromDataBase() {
    return Single.fromCallable(() -> SQLite.select().from(Crime.class).where().queryList())
        .subscribeOn(Schedulers.io());
  }

  public Single<List<Crime>> getCrimesFromService() {
    return crimeService.getCrimes()
        .observeOn(Schedulers.io())
        .doOnSuccess(crimes -> {
          SQLite.delete().from(Crime.class).execute();
          Stream.of(crimes).forEach(BaseModel::insert);
        })
        .subscribeOn(Schedulers.io());
  }

  public Single<Crime> insertCrime(Crime crime) {
    return crimeService.createCrime(crime)
        .doOnSuccess(BaseModel::insert)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Single<Crime> updateCrime(String id, Crime crime) {
    return crimeService.updateCrime(id, crime)
        .doOnSuccess(BaseModel::update)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io());
  }

  public Observable<List<Crime>> getCrimes() {
    return Observable.concatDelayError(getCrimesFromDataBase().toObservable(), getCrimesFromService().toObservable())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
