package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button about_button = findViewById(R.id.aboutButton);
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new AboutFragment());
            }
        });
    }

    public void newGame(View view){
        Intent intent = new Intent(this, NewGame.class);
        startActivity(intent);
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout2, fragment);
        fragmentTransaction.commit();
    }
}