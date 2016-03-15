package com.example.matt.labtask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.util.Log;
import android.graphics.drawable.Drawable;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    RelativeLayout background;

    EditText userInput;
    ImageView circleImage;
    TextView displayText;
    Button userButton;

    // animate the eightball model
    private void moveEightBall(EightBallModel fortune){

        android.view.animation.Animation myanimation;
        myanimation = new android.view.animation.AlphaAnimation(0.0f, 1.0f);
        myanimation.setDuration(400);
        myanimation.setStartOffset(0);

        String randResp;
        String magicSrc = "";
        randResp = fortune.magicResponse();
        magicSrc = fortune.magicBackground();
        int resID = getResources().getIdentifier(magicSrc, "drawable", getPackageName());
        this.displayText.startAnimation(myanimation);
        displayText.setText(randResp);
        circleImage.setBackgroundResource(resID);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ArrayList<String> newResponses = new ArrayList<>();

        newResponses.add("Perfect and awesome!");
        newResponses.add("Yes");
        newResponses.add("Signs point to yes");

        final EightBallModel fortune;
        fortune = new EightBallModel(newResponses);
        String randResp = fortune.magicResponse();

        this.background = (RelativeLayout)findViewById(R.id.myLayout);

        this.userInput = (EditText)findViewById(R.id.editText);
        this.circleImage = (ImageView)findViewById(R.id.imageView);
        this.displayText = (TextView)findViewById(R.id.textView);
        this.userButton = (Button)findViewById(R.id.button);

        // set on click event for the button
        this.userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                moveEightBall(fortune);
            }

        });


        // set the onchange event for the text field

        this.userInput.setOnKeyListener(new android.view.View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, android.view.KeyEvent event) {

                if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                    switch (keyCode) {
                        case android.view.KeyEvent.KEYCODE_DPAD_CENTER:
                        case android.view.KeyEvent.KEYCODE_ENTER:

                            moveEightBall(fortune);

                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


    }
}
