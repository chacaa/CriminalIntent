package com.xmartlabs.scasas.criminalintent.model;

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public boolean isSolved() {
    return solved;
  }

  public void setSolved(boolean solved) {
    this.solved = solved;
  }

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
