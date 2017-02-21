package com.xmartlabs.scasas.criminalintent.model;

import org.parceler.Parcel;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by scasas on 2/2/17.
 */
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
@Parcel
public class Crime {
  Date date;
  UUID id;
  boolean solved;
  String title;
}
