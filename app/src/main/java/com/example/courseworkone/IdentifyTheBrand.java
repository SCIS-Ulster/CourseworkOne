package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class IdentifyTheBrand extends AppCompatActivity implements AdapterView.OnItemClickListener,
        AdapterView.OnItemSelectedListener {

    String[] brands = { "Audi", "Bentley", "BMW", "FIAT", "Ford", "Honda", "Hyundai", "Jaguar", "Mercedes-Benz", "Toyota"};
    ImageView imageView;
    Button button;
    TextView result;
    TextView brandName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_brand);

        // Setup dropdown menu
        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, brands);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        // set up random image
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.submitButton);

        // set random image
        int rand = randomGen();
        setRandomImage(rand);

        //Get dropdown string value
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String submittedAnswer = spin.getSelectedItem().toString();
                result = (TextView) findViewById(R.id.textView2);
                brandName = (TextView) findViewById(R.id.textView3);


                if(submittedAnswer.equals(brands[rand])){
                    result.setTextColor(Color.GREEN);
                    result.setText("CORRECT!");
                }else{
                    result.setTextColor(Color.RED);
                    result.setText("WRONG!");
                    }

                brandName.setText(brands[rand]);
                button.setText("Next");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(getIntent());
                    }
                });
            }
            });


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void setRandomImage(int rand){
        String filename = "/sdcard/images/" + brands[rand] + "/" + brands[rand] + randomGen() + ".jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filename);
        imageView.setImageBitmap(bitmap);
    }

    public int randomGen(){
        int max = 10;
        int min = 1;
        int range = max - min;
        int rand = ((int)(Math.random() * range)) + min;
        return rand;
    }
}