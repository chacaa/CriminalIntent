package com.xmartlabs.scasas.criminalintent.ui.crime.list;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.xmartlabs.scasas.criminalintent.R;
import com.xmartlabs.scasas.criminalintent.model.Crime;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by scasas on 2/8/17.
 */
public class CrimeAdapter extends RecyclerView.Adapter<CrimeAdapter.CrimeHolder> {
  protected List<Crime> crimes;

  public CrimeAdapter(List<Crime> crimes) {
    this.crimes = crimes;
  }

  @Override
  public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
    View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
    return new CrimeHolder(view);
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

  class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    @BindView(R.id.list_item_crime_date_textview)
    TextView dateTextView;
    @BindView(R.id.list_item_crime_solved_checkbox)
    CheckBox solvedCheckBox;
    @BindView(R.id.list_item_crime_title_textview)
    TextView titleTextView;

    public CrimeHolder(View itemView) {
      super(itemView);
      itemView.setOnClickListener(this);
      ButterKnife.bind(this, itemView);
    }

    public void bind(Crime crime) {
      dateTextView.setText(crime.getDate().toString());
      solvedCheckBox.setChecked(crime.isSolved());
      titleTextView.setText(crime.getTitle());
    }

    @Override
    public void onClick(View v) {
      makeToast(R.string.clicked);
    }

    private void makeToast(@StringRes int toastMessage) {
      Toast
          .makeText(itemView.getContext(), toastMessage, Toast.LENGTH_SHORT)
          .show();
    }
  }
}

