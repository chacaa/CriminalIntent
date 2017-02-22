package com.xmartlabs.scasas.criminalintent.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by scasas on 2/21/17.
 */
@Database(name = AppDataBase.NAME, version = AppDataBase.VERSION)
public class AppDataBase {
  public static final String NAME = "criminalIntent_database";
  public static final int VERSION = 1;
}
