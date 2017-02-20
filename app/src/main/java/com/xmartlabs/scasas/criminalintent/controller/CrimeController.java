package com.xmartlabs.scasas.criminalintent.controller;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.annimon.stream.Objects;
import com.annimon.stream.Stream;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import lombok.Getter;

/**
 * Created by scasas on 2/7/17.
 */
public class CrimeController {
  @Getter
  private final static CrimeController INSTANCE = new CrimeController();

  @Getter
  private final List<Crime> crimes = new ArrayList<>();

  public Crime getCrime(@NonNull UUID id) {
    return Stream.of(crimes)
        .filter(crime -> Objects.equals(crime.getId(), id))
        .findFirst()
        .orElse(null);
  }

  public void addCrime(Crime crime) {
    crimes.add(crime);
  }
}
