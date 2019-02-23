package com.example.boris.mamafoodjob.Fragments;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.mamafoodjob.Preview;
import com.example.boris.mamafoodjob.R;

public class SecondFragment extends Fragment {
    public static final int PICK_AVATAR = 777;
    public static final int PICK_PASSPORT = 516;
    static Preview preview;
    private ImageView avatar, passport;
    // newInstance constructor for creating fragment with arguments
    public static SecondFragment newInstance(Preview prev) {
        SecondFragment fragmentFirst = new SecondFragment();
        Bundle args = new Bundle();
        preview = prev;
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    public SecondFragment() {
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
        View view = inflater.inflate(R.layout.preview_2, container, false);

        avatar = view.findViewById(R.id.avatar);
        passport = view.findViewById(R.id.passport);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_AVATAR);
            }
        });
        passport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_PASSPORT);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_AVATAR) {
            avatar.setImageURI(data.getData());
        }if (requestCode == PICK_PASSPORT) {
            passport.setImageURI(data.getData());
        }
    }
}
