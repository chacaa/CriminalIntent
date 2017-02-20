package com.xmartlabs.scasas.criminalintent.model;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.UUID;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by scasas on 2/2/17.
 */
@Data
@Builder
public class Crime {
  private Date date;
  @NonNull
  private final UUID id = generateUniqueIdentifier();
  private boolean solved;
  private String title;

  private UUID generateUniqueIdentifier() {
    return UUID.randomUUID();
  }
}
