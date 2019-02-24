package com.example.boris.mamafoodjob;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.boris.mamafoodjob.Adapters.recyclerAdapterMain;
import com.example.boris.mamafoodjob.Managers.LoginChecker;
import com.example.boris.mamafoodjob.Model.Mom;
import com.example.boris.mamafoodjob.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private static final String  TAG = "myTag";
    List<User> users = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDefaults();

        getUsers();

        recyclerAdapterMain adapter = new recyclerAdapterMain(users, this);
        recyclerView.setAdapter(adapter);
    }

    private void getUsers() {
        users.add(new User("vova", "hchu kartochu", null, "revuzkogo 32", "2018/12/12"));
    }


    private void setDefaults(){
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //add menu to top
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.logOut:
                LoginChecker loginChecker = new LoginChecker(this);
                loginChecker.clearPreference();
                startActivity(new Intent(this, Preview.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
