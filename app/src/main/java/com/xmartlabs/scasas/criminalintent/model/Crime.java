package com.xmartlabs.scasas.criminalintent.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.xmartlabs.scasas.criminalintent.database.AppDataBase;

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
@Table(database = AppDataBase.class, cachingEnabled = true, cacheSize = 100)
public class Crime extends BaseModel {
  @Column
  Date date;
  @PrimaryKey
  UUID id;
  @Column
  boolean solved;
  @Column
  String title;
}
