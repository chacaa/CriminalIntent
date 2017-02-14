package com.xmartlabs.scasas.criminalintent.model;

import java.util.Date;
import java.util.UUID;

/**
 * Created by scasas on 2/2/17.
 */
public class Crime {
  private Date date;
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

  public static final class Builder {
    private Date date;
    private boolean solved;
    private String title;

    public Builder() {
    }

    public Builder date(Date val) {
      date = val;
      return this;
    }

    public Builder solved(boolean val) {
      solved = val;
      return this;
    }

    public Builder title(String val) {
      title = val;
      return this;
    }

    public Crime build() {
      return new Crime(this);
    }
  }

  private Crime(Builder builder) {
    setDate(builder.date);
    setSolved(builder.solved);
    setTitle(builder.title);
  }
}
