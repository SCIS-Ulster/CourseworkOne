package com.example.courseworkone;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

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
    public static final String TAG = "logs";

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

        // set random image
        final int rand = randomGen();
        setRandomImage(rand);

        // get brand name
        String answer = brands[rand];

        // final answer will hold our correct guesses at there index
        int lengthOfAnswer = answer.length();
        final char[] finalAnswer = new char[lengthOfAnswer];

        // Populate dashes text box with dashes of brand length
        for(int i = 0; i < lengthOfAnswer; i++){
            hintText.setText(hintText.getText() + "_");
            finalAnswer[i] = '_';
        }

        // Check if any text in box, if there is let user hit button
        // Without this the user can hit the button with empty text and we get an error.
        button.setEnabled(false);
        hintEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().length() == 0){
                    button.setEnabled(false);
                }else{
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the char input
                String stringInput = hintEdit.getText().toString();
                char input = stringInput.charAt(0);
                // clear edittext box
                hintEdit.setText("");

                // set flag to false
                flag = false;
                // check input against answer
                for(int i = 0; i < lengthOfAnswer; i++){
                    if(input == answer.charAt(i)){
                        finalAnswer[i] = answer.charAt(i);
                        // if any are found set flag to true
                        flag = true;
                    }
                }
                // if flag is false, none where found, one life gone
                if(flag == false) {
                    counter++;
                }

                // add correct chars to final string
                String newString = new String(finalAnswer);

                // If its right
                if(newString.equals(answer)){
                    button.setText("Next");
                    button.setEnabled(true);
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
                    button.setEnabled(true);
                    button.setText("Next");
                    hintRightWrong.setText("WRONG!");
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
        int min = 0;
        int range = max - min;
        int rand = ((int)(Math.random() * range)) + min;
        return rand;
    }

}