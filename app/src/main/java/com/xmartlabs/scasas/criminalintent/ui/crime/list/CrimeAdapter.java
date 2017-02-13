package com.xmartlabs.scasas.criminalintent.ui.crime.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.xmartlabs.scasas.criminalintent.ui.common.OnTappedListener;
import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 2/8/17.
 */
public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {
  private final List<Crime> crimes;
  private final OnTappedListener<Crime> onCrimeTappedListener;

  public CrimeAdapter(@NonNull List<Crime> crimes, OnTappedListener<Crime> onCrimeTappedListener) {
    this.crimes = crimes;
    this.onCrimeTappedListener = onCrimeTappedListener;
  }

  @Override
  public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
    return new CrimeHolder(view, onCrimeTappedListener);
  }

  @Override
  public void onBindViewHolder(CrimeHolder holder, int position) {
    Crime crime = crimes.get(position);
    holder.bind(crime);
  }

  @Override
  public int getItemCount() {
    return crimes.size();
  }

  static class CrimeHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.list_item_crime_date_textview)
    TextView dateTextView;
    @BindView(R.id.list_item_crime_solved_checkbox)
    CheckBox solvedCheckBox;
    @BindView(R.id.list_item_crime_title_textview)
    TextView titleTextView;

    private OnTappedListener<Crime> onCrimeTappedListener;

    public CrimeHolder(View itemView, OnTappedListener<Crime> onCrimeTappedListener) {
      super(itemView);
       ButterKnife.bind(this, itemView);
       this.onCrimeTappedListener = onCrimeTappedListener;
    }

    public void bind(Crime crime) {
      dateTextView.setText(crime.getDate().toString());
      solvedCheckBox.setChecked(crime.isSolved());
      titleTextView.setText(crime.getTitle());
      itemView.setOnClickListener(v -> onCrimeTappedListener.call(crime));
    }
  }
}

