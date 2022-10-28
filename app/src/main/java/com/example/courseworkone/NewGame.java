package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class NewGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
    }

    public void identifyTheBrand(View view){
        Intent intent = new Intent(this, IdentifyTheBrand.class);
        startActivity(intent);
    }

    public void hints(View view){
        Intent intent = new Intent(this, Hints.class);
        startActivity(intent);
    }
}