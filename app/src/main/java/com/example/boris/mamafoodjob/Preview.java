package com.example.boris.mamafoodjob;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.lang.reflect.Field;

public class Preview extends AppCompatActivity {

    private ViewPager pager;
    //private int[] layouts = {R.layout.preview_1, R.layout.preview_2, R.layout.preview_3, R.layout.preview_4};
    private AdapterPreview adapter;

    private LinearLayout dots_layout;
    private ImageView[] dotImg;

    public Button next;

    private String name, password, avatar, pasport, date, description;
    private void pos3(View viewGroup){

    }
    private void pos4(View viewGroup){

    }
    public void loginIn(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            new LoginChecker(Preview.this).write();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
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
