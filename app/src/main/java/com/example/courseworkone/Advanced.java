package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Advanced extends AppCompatActivity {

    String[] brands = { "Audi", "Bentley", "BMW", "FIAT", "Ford", "Honda", "Hyundai", "Jaguar", "Mercedes-Benz", "Toyota"};
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    EditText editText1;
    EditText editText2;
    EditText editText3;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView scoreText;
    Button button;
    String brand1;
    String brand2;
    String brand3;
    int counter;
    int score;
    boolean flag1;
    boolean flag2;
    boolean flag3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);

        // set up three image views
        imageView1 = (ImageView) findViewById(R.id.advancedImage1);
        imageView2 = (ImageView) findViewById(R.id.advancedImage2);
        imageView3 = (ImageView) findViewById(R.id.advancedImage3);

        // set up three edittext boxes
        editText1 = (EditText) findViewById(R.id.advancedEdit1);
        editText2 = (EditText) findViewById(R.id.advancedEdit2);
        editText3 = (EditText) findViewById(R.id.advancedEdit3);

        // set up three text views
        textView1 = (TextView) findViewById(R.id.advancedText1);
        textView2 = (TextView) findViewById(R.id.advancedText2);
        textView3 = (TextView) findViewById(R.id.advancedText3);
        textView4 = (TextView) findViewById(R.id.advancedText4);

        // set up next button
        button = (Button) findViewById(R.id.advancedLevelButton);

        // score
        scoreText = (TextView) findViewById(R.id.scoreText);

        // Initialising our variables
        brand1 = null;
        brand2 = null;
        brand3 = null;
        counter = 0;
        score = 0;
        flag1 = true;
        flag2 = true;
        flag3 = true;

        // Get 3 unique car brands
        boolean stop = false;
        while(stop != true){
            brand1 = brands[randomGen()];
            brand2 = brands[randomGen()];
            brand3 = brands[randomGen()];
            // If all different end the loop
            if(brand1 != brand2 && brand2 != brand3 && brand1 != brand3){
                stop = true;
            }
        }

        // Set our images
        setRandomImage(brand1, imageView1);
        setRandomImage(brand2, imageView2);
        setRandomImage(brand3, imageView3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean a = true;
                boolean b = true;
                boolean c = true;

                // if the strings match, textbox is locked, flag1 changes to false, score is incremented
                // flag1 cannot be changed back to true, so score cannot be incremented again
                if(flag1 == true){
                    a = checkStrings(brand1, editText1);
                    if(a == true){
                        flag1 = false;
                    }
                }
                if(flag2 == true){
                    b = checkStrings(brand2, editText2);
                    if(b == true){
                        flag2 = false;
                    }
                }
                if(flag3 == true){
                    c = checkStrings(brand3, editText3);
                    if(c == true){
                        flag3 = false;
                    }
                }

                // increase counter for every click
                counter++;

                // all correct
                if(a==true && b==true && c==true){
                    textView4.setText("CORRECT!");
                    textView4.setTextColor(Color.GREEN);

                    button.setText("Next");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            startActivity(getIntent());
                        }
                    });
                }

                // If count is 3, lives all gone, reveal answers and set button to start new game.
                if(counter == 3){
                    textView4.setText("WRONG!");
                    textView4.setTextColor(Color.RED);

                    if(a == false){
                        textView1.setText(brand1);
                        textView1.setTextColor(Color.YELLOW);

                    }
                    if(b == false){
                        textView2.setText(brand2);
                        textView2.setTextColor(Color.YELLOW);

                    }
                    if(c == false){
                        textView3.setText(brand3);
                        textView3.setTextColor(Color.YELLOW);
                    }

                    // Start new game
                    button.setText("Next");
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(getIntent());
                        }
                    });
                }

                // set score
                scoreText.setText(Integer.toString(score));
            }
        });
    }

    // check if strings match
    private boolean checkStrings(String a, EditText b){
        // if they match, increment score, disable edittext and set colour to green
        if(a.equals(b.getText().toString())){
            b.setTextColor(Color.GREEN);
            b.setEnabled(false);
            score++;
            return true;
        }
        else{
            b.setTextColor(Color.RED);
            return false;
        }
    }

    // Sets image with brand name and random number
    public void setRandomImage(String name, ImageView imageView){
        String filename = "/sdcard/images/" + name + "/" + name + randomGen() + ".jpg";
        Bitmap bitmap = BitmapFactory.decodeFile(filename);
        imageView.setImageBitmap(bitmap);
    }

    // Gets a random number between 0-9
    public int randomGen(){
        int max = 10;
        int min = 0;
        int range = max - min;
        int rand = ((int)(Math.random() * range)) + min;
        return rand;
    }
}