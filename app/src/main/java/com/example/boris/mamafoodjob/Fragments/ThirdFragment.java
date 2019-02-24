package com.example.boris.mamafoodjob.Fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.mamafoodjob.Preview;
import com.example.boris.mamafoodjob.R;

public class ThirdFragment extends Fragment {
    static Preview preview;
    // newInstance constructor for creating fragment with arguments
    public static ThirdFragment newInstance(Preview prev) {
        ThirdFragment thirdFragment = new ThirdFragment();
        Bundle args = new Bundle();
        preview = prev;
        thirdFragment.setArguments(args);
        return thirdFragment;
    }

    public ThirdFragment() {
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
        View view = inflater.inflate(R.layout.preview_3, container, false);

        EditText description = view.findViewById(R.id.description);
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                preview.description = s.toString();
            }
        });

        return view;
    }
}
