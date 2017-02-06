package com.xmartlabs.scasas.criminalintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by scasas on 2/2/17.
 */
public class Crime {
  private Date date = new Date();
  private UUID id = generateUniqueIdentifier();
  private boolean solved;
  private String title;

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  private UUID generateUniqueIdentifier() {
    return UUID.randomUUID();
  }
}
