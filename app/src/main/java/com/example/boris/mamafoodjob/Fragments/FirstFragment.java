package com.example.boris.mamafoodjob.Fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.mamafoodjob.Preview;
import com.example.boris.mamafoodjob.R;

public class FirstFragment extends Fragment {
    static Preview preview;
    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(Preview prev) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        preview = prev;
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public FirstFragment() {
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
        View view = inflater.inflate(R.layout.preview_1, container, false);

        TextView signUp = view.findViewById(R.id.signUp);
        signUp.setPaintFlags(signUp.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        TextView signIn = view.findViewById(R.id.signIn);
        signIn.setPaintFlags(signIn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        final EditText login = view.findViewById(R.id.login);
        final EditText password = view.findViewById(R.id.password);
        final EditText name = view.findViewById(R.id.momName);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(getContext(), "change title or password", Toast.LENGTH_LONG).show();
                }else{
                    preview.loginIn();
                }
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (login.getText().toString().equals("") || password.getText().toString().equals("")){
                    Toast.makeText(getContext(), "change title or password", Toast.LENGTH_LONG).show();
                }else{
                    preview.loadSlide();
                }
            }
        });
        login.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                preview.email = s.toString();
            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                preview.password = s.toString();
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                preview.name = s.toString();
            }
        });

        return view;
    }
}
