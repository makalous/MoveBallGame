package com.example.moveballgame;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Accelerometer accelerometer;
    private ImageView ball;
    float height, width;
    Point size;
    Display display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometer = new Accelerometer(this);
        ball = findViewById(R.id.random);
        ball.setImageDrawable(getResources().getDrawable(R.drawable.ic_ball));
        size = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        width = size.x;
        height = size.y;
        ball.setX(width/2);
        ball.setY(width/2);

        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {

                if (tx*700+width/2<width && tx*700+width/2>0 && ty*700+height/2<height && ty*700+height/2>0 ){
                    ball.setX(tx*700+width/2);
                    ball.setY(ty*700+height/2);
                }
                else {
                    Toast toast=Toast. makeText(getApplicationContext(),"You Lost!",Toast. LENGTH_SHORT);
                    toast.show();
                    ball.setX(width/2);
                    ball.setY(height/2);
                    //onPause();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        accelerometer.register();

    }

    @Override
    protected void onPause() {
        super.onPause();

        accelerometer.unregister();

    }



}
