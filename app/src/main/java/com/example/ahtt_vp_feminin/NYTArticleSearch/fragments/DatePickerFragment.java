package com.example.ahtt_vp_feminin.NYTArticleSearch.fragments;

import android.app.DatePickerDialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import java.util.Calendar;
/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

public class DatePickerFragment extends DialogFragment {
    public void setOndateSet(DatePickerDialog.OnDateSetListener ondateSet) {
        this.ondateSet = ondateSet;
    }
    DatePickerDialog.OnDateSetListener ondateSet;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), ondateSet, year, month, day);
    }
}
