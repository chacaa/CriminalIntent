package com.xmartlabs.scasas.criminalintent;

import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Single;

/**
 * Created by scasas on 2/23/17.
 */

public interface Service {
  @GET("crimes")
  Single<List<Crime>> getCrimes();
}
