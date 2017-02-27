package com.xmartlabs.scasas.criminalintent;

import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Single;

/**
 * Created by scasas on 2/23/17.
 */
public interface Service {
  @GET("crimes")
  Single<List<Crime>> getCrimes();
  @POST("crimes")
  Single<Crime> createCrime(@Body Crime crime);
  @PUT("crimes/{id}")
  Single<Crime> updateCrime(@Path("id")String id, @Body Crime crime);
}
