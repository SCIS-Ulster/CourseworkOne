package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class IdentifyTheCar extends AppCompatActivity {

    String[] brands = { "Audi", "Bentley", "BMW", "FIAT", "Ford", "Honda", "Hyundai", "Jaguar", "Mercedes-Benz", "Toyota"};
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    Button button;
    TextView answerText;
    TextView resultText;
    String brand1;
    String brand2;
    String brand3;
    String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_the_car);

        imageView1 = (ImageView) findViewById(R.id.imageCar1);
        imageView2 = (ImageView) findViewById(R.id.imageCar2);
        imageView3 = (ImageView) findViewById(R.id.imageCar3);
        answerText = (TextView) findViewById(R.id.textViewAnswer);
        resultText = (TextView) findViewById(R.id.textViewResult);
        button = (Button) findViewById(R.id.buttonCar);

        brand1 = null;
        brand2 = null;
        brand3 = null;

        // Get 3 unique cars
        boolean stop = false;
        while(stop != true){
            brand1 = brands[randomGen()];
            brand2 = brands[randomGen()];
            brand3 = brands[randomGen()];
            if(brand1 != brand2 && brand2 != brand3 && brand1 !=brand3){
                stop = true;
            }
        }

        // Set our images
        setRandomImage(brand1, imageView1);
        setRandomImage(brand2, imageView2);
        setRandomImage(brand3, imageView3);

        // Set a random choice of three displayed images to be the text
        String[] randomAnswer = {brand1, brand2, brand3};
        int rand = randomGenSmall();
        answerText.setText(randomAnswer[rand]);

        // Get our answer as string
        answer = randomAnswer[rand];

        // Check if string match when button clicked
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStrings(answer, brand1);
                imageView1.setEnabled(false);
                imageView2.setEnabled(false);
                imageView3.setEnabled(false);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStrings(answer, brand2);
                imageView1.setEnabled(false);
                imageView2.setEnabled(false);
                imageView3.setEnabled(false);
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkStrings(answer, brand3);
                imageView1.setEnabled(false);
                imageView2.setEnabled(false);
                imageView3.setEnabled(false);
            }
        });

        // new activity with new images if click next
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(getIntent());
            }
        });
    }

    private void checkStrings(String a, String b){
        if(a.equals(b)){
            resultText.setText("CORRECT!");
            resultText.setTextColor(Color.GREEN);

        }
        else{
            resultText.setText("WRONG!");
            resultText.setTextColor(Color.RED);
        }
    }

    public void setRandomImage(String name, ImageView imageView){
        String filename = "/sdcard/images/" + name + "/" + name + randomGen() + ".jpg";
        System.out.println("file" + filename);
        Bitmap bitmap = BitmapFactory.decodeFile(filename);
        imageView.setImageBitmap(bitmap);
    }

    public int randomGen(){
        int max = 10;
        int min = 0;
        int range = max - min;
        int rand = ((int)(Math.random() * range)) + min;
        return rand;
    }

    public int randomGenSmall(){
        int max = 3;
        int min = 0;
        int range = max - min;
        int rand = ((int)(Math.random() * range)) + min;

        return rand;
    }

}