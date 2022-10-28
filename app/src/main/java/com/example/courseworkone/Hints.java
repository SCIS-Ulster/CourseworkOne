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

public class Hints extends AppCompatActivity {

    String[] brands = { "Audi", "Bentley", "BMW", "FIAT", "Ford", "Honda", "Hyundai", "Jaguar", "Mercedes-Benz", "Toyota"};
    ImageView imageView;
    Button button;
    TextView hintText;
    TextView hintResult;
    TextView hintRightWrong;
    EditText hintEdit;
    int counter;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hints);

        imageView = (ImageView) findViewById(R.id.imageViewHint);
        button = (Button) findViewById(R.id.buttonHint);
        hintText = (TextView) findViewById(R.id.textViewHint);
        hintEdit = (EditText) findViewById(R.id.editTextHint);
        hintResult = (TextView) findViewById(R.id.hintResult);
        hintRightWrong = (TextView) findViewById(R.id.hintRightWrong);
        flag = false;

        // set random image
        final int rand = randomGen();
        setRandomImage(rand);

        // get brand name and set to uppercase
        String answer = brands[rand].toUpperCase();
        System.out.println("HERE" + answer);

        // set dashes to that length
        int lengthOfAnswer = answer.length();
        final char[] Y = new char[lengthOfAnswer];
        for(int i = 0; i < lengthOfAnswer; i++){
            hintText.setText(hintText.getText() + "_");
            Y[i] = '_';
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get users guess
                String x = hintEdit.getText().toString().toUpperCase();
                // clear edittext box
                hintEdit.setText("");
                // check input against answer
                for(int i = 0; i < lengthOfAnswer; i++){
                    if(x.charAt(0) == answer.charAt(i)){
                        Y[i] = answer.charAt(i);
                        // if any are found set flag to true
                        flag = true;
                    }
                }
                // if flag is false, none where found, one life gone
                if(flag == false) {
                    counter++;
                }

                // add correct chars to final string
                String newString = new String(Y);

                // If its right
                if(newString.equals(answer)){
                    hintResult.setText(answer);
                    button.setText("Next");
                    hintRightWrong.setText("CORRECT");
                    hintRightWrong.setTextColor(Color.GREEN);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(getIntent());
                        }
                    });
                }

                // if lives are gone
                if(counter == 3){
                    hintResult.setText(answer);
                    button.setText("Next");
                    hintRightWrong.setText("WRONG");
                    hintRightWrong.setTextColor(Color.RED);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(getIntent());
                        }
                    });
                }

                // add found letters to dashes text
                hintText.setText(newString);
            }
        });
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