package com.example.boris.mamafoodjob;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boris.mamafoodjob.Adapters.AdapterPreview;
import com.example.boris.mamafoodjob.Fragments.SecondFragment;
import com.example.boris.mamafoodjob.Managers.LoginChecker;
import com.example.boris.mamafoodjob.Model.Mom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.lang.reflect.Field;

public class Preview extends AppCompatActivity {
    private static final int MY_PERMISSION = 940;
    private StorageReference mStorageRef;
    public ViewPager pager;
    private DatabaseReference myRef;
    //private int[] layouts = {R.layout.preview_1, R.layout.preview_2, R.layout.preview_3, R.layout.preview_4};
    private AdapterPreview adapter;
    private LinearLayout dots_layout;
    private ImageView[] dotImg;
    public Button next;

    public String name, password, avatar, passport, date, description;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reqwestPremissions();
        //check if was before
        //new Manager(this).clearPreference();  - clear notice that u was there

        if (new LoginChecker(this).checkPreference()){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        //////////////////////
        if (Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_preview);
        hideToolbar();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(10);
        adapter = new AdapterPreview(getSupportFragmentManager(),  this);
        pager.setAdapter(adapter);



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int i) {
                createDots(i);
                if(i == 4 - 1){
                    next.setText("Start");
                }else{
                    next.setText("Next");
                }

            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

//next button
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //new Manager(Preview.this).write(); - set that u was there
                loadSlide();
            }
        });

        //dots
        dots_layout = findViewById(R.id.dots);
        createDots(0);
    }

    private boolean reqwestPremissions() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSION);
            return false;
        }else if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSION);
            return false;
        }
        return true;
    }

    private void hideToolbar(){
        int currentApiVersion = android.os.Build.VERSION.SDK_INT;
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        if (currentApiVersion >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(flags);
            final View decorView = getWindow().getDecorView();
            decorView
                    .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                        @Override
                        public void onSystemUiVisibilityChange(int visibility) {
                            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                                decorView.setSystemUiVisibility(flags);
                            }
                        }
                    });
        }
    }

    private void createDots(int position){
        if (dots_layout != null){
            dots_layout.removeAllViews();
        }

        dotImg = new ImageView[4];

        for (int i = 0; i < 4; i++) {

            dotImg[i] = new ImageView(this);
            if(i == position){
                dotImg[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.selected_dots));
            }else{
                dotImg[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.notselected_dots));
            }

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);

            dots_layout.addView(dotImg[i], params);
        }
    }

    public void loadSlide(){
        int next_slide = pager.getCurrentItem() + 1;

        if (next_slide < 4){
            pager.setCurrentItem(next_slide);
        }else{
            saveDataToDB();
        }
    }

    private void saveDataToDB() {

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        mStorageRef = FirebaseStorage.getInstance().getReference();
        continueSavingDate();

        /*firebaseAuth.createUserWithEmailAndPassword(name, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    continueSavingDate();
                }else{
                    Toast.makeText(getApplicationContext(), "registration error - " + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });*/

    }
    private void continueSavingDate(){
        Uri a = Uri.parse(Environment.getExternalStorageState() + avatar);
        Uri b = Uri.parse(Environment.getExternalStorageState() + passport);
        String userID = firebaseAuth.getCurrentUser().getUid();
        boolean exit = true;

        StorageReference storageReference = mStorageRef.child("images/users/" + userID + "/" + name + "1" + ".jpg");
        exit = storageReference.putFile(a).addOnCompleteListener((Activity) getApplicationContext(), new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (!task.isSuccessful())
                Toast.makeText(getApplicationContext(), task.getException() + "", Toast.LENGTH_LONG).show();
            }
        }).isSuccessful();
        if (!exit) return;
        storageReference = mStorageRef.child("images/users/" + userID + "/" + name + "2" + ".jpg");
        exit = storageReference.putFile(b).addOnCompleteListener((Activity) getApplicationContext(), new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (!task.isSuccessful())
                    Toast.makeText(getApplicationContext(), task.getException() + "", Toast.LENGTH_LONG).show();
            }
        }).isSuccessful();
        if (exit) return;
        myRef = FirebaseDatabase.getInstance().getReference("moms");
        String id = myRef.push().getKey();
        Mom mom = new Mom(name, description, date, avatar, passport);
        boolean success = myRef.child(id).setValue(mom).isSuccessful();
        if (!success) {
            Toast.makeText(getApplicationContext(), "recording mom error error", Toast.LENGTH_LONG).show();
        }else{
            new LoginChecker(Preview.this).write();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }
    public void loginIn(){

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SecondFragment.PICK_AVATAR && resultCode == Activity.RESULT_OK) {
            avatar = data.getData().getPath();
        }
        if (requestCode == SecondFragment.PICK_PASSPORT && resultCode == Activity.RESULT_OK) {

        }
    }

}
