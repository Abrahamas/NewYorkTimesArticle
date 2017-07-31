package com.example.ahtt_vp_feminin.NYTArticleSearch.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.ahtt_vp_feminin.NYTArticleSearch.R;
import com.example.ahtt_vp_feminin.NYTArticleSearch.models.SearchFilter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by AHTT - V P-FEMININ on 7/23/2017.
 */

public class ArticleListFilterFragment extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String SEARCH_FILTER = "search_filter";

    SearchFilter searchFilter;

    Date     beginDate;
    EditText etDate;
    CheckBox cbArts;
    CheckBox cbFashion;
    CheckBox cbSports;
    Spinner  spinner;

    // private OnFragmentInteractionListener mListener;

    public ArticleListFilterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param searchFilter search filter to use as current settings
     * @return A new instance of fragment ArticleListFilterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArticleListFilterFragment newInstance(SearchFilter searchFilter) {
        ArticleListFilterFragment fragment = new ArticleListFilterFragment();

        // parcel and inject the search filter if it isn't null.
        if (searchFilter != null) {
            Bundle args = new Bundle();
            //args.putParcelable("search_filter", Parcels.wrap(searchFilter));
            fragment.setArguments(args);
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //this.searchFilter = Parcels.unwrap(getArguments().getParcelable(SEARCH_FILTER));
        }
    }

    private void setupSpinner(View view) {
        // 1. Get the spinner
        this.spinner = (Spinner) view.findViewById(R.id.spinner);
        // 2. Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sort_order_items, android.R.layout.simple_spinner_item);
        // 3. Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 4. Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // 5. check if there was a search filter passed in
        if (searchFilter != null) {
            spinner.setSelection(searchFilter.isSortOldest() ? 0 : 1);
        }
    }

    private void setupDatePicker(View view) {

        // 1. init the date instance variable
        this.etDate = (EditText) view.findViewById(R.id.etDate);
        this.etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setOndateSet(ondate);
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });


        // 2. If a search filter was passed in then use it to set default
        if (this.searchFilter != null && searchFilter.getBenginDate() != null) {
            SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
            String date =  dateF.format(searchFilter.getBenginDate());
            etDate.setText(date);
        }
    }

    private void setupCheckBoxes(View view) {

        // 1. init the views
        this.cbArts = (CheckBox) view.findViewById(R.id.cbArts);
        this.cbFashion  = (CheckBox) view.findViewById(R.id.cbFashion);
        this.cbSports   = (CheckBox) view.findViewById(R.id.cbSports);

        // 2. if there was a search filter passed in, then set the defaults
        if (searchFilter != null) {
            this.cbArts.setChecked(searchFilter.isArts());
            this.cbFashion.setChecked(searchFilter.isFashionAndStyle());
            this.cbSports.setChecked(searchFilter.isSports());
        }
    }



    private void setupButton(View view) {
        Button saveButton = (Button) view.findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onButtonPressed();
                closefragment();
            }
        });
    }

    private void closefragment() {
        dismiss();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_article_list_filter, container, false);

        // 2. setup views
        setupSpinner(view);
        setupDatePicker(view);
        setupButton(view);
        setupCheckBoxes(view);

        // 3. return view
        return view;
    }

    /*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {
        if (mListener != null) {

            // 1. set the appropriate search filter values.
            SearchFilter filter = new SearchFilter();
            filter.setSortOldest(spinner.getSelectedItemPosition() == 0 ? true : false);
            filter.setArts(cbArts.isChecked());
            filter.setFashionAndStyle(cbFashion.isChecked());
            filter.setSports(cbSports.isChecked());
            filter.setBeginDate(beginDate);

            // 2. send the filter via listener
            mListener.onFragmentInteraction(filter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

    DatePickerDialog.OnDateSetListener ondate = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            SimpleDateFormat dateF = new SimpleDateFormat("EEE, d MMM yyyy", Locale.getDefault());
            Calendar cal = new GregorianCalendar();
            cal.set(year, month, dayOfMonth);
            String date =  dateF.format(cal.getTime());

            beginDate = cal.getTime();
            etDate.setText(date);
            Log.d("DEBUG", year + "" + month + "" + dayOfMonth + "");
        }
    };



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    /*
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(SearchFilter searchFilter);
    }
    */
}

//}

