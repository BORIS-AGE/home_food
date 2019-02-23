package com.example.boris.mamafoodjob.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boris.mamafoodjob.Managers.DatePicker;
import com.example.boris.mamafoodjob.Preview;
import com.example.boris.mamafoodjob.R;

import java.util.Calendar;

public class ForthFragment extends Fragment {
    private static final int REQUEST_CODE_DATE = 664;
    ImageView avatar, passport;
    static Preview preview;
    TextView date;
    // newInstance constructor for creating fragment with arguments
    public static ForthFragment newInstance(Preview prev) {
        ForthFragment fragmentFirst = new ForthFragment();
        Bundle args = new Bundle();
        preview = prev;
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public ForthFragment() {
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preview_4, container, false);

        date = view.findViewById(R.id.datePicker);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = new DatePicker();
                datePicker.setTargetFragment(ForthFragment.this, REQUEST_CODE_DATE);
                datePicker.show(getFragmentManager(), "datePicker");
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DATE && resultCode == Activity.RESULT_OK) {
            int year = data.getExtras().getInt("year");
            int month = data.getExtras().getInt("month");
            int dayOfMonth = data.getExtras().getInt("dayOfMonth");

            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month, dayOfMonth);
            date.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
            preview.date = year + "/" + (month + 1) + "/" + dayOfMonth;
        }
    }
}
