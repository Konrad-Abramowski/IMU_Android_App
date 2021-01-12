package com.example.imuandroidapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class ScrollingActivity extends AppCompatActivity {
    private FloatingActionButton welcomeBtn;

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
    private Magnetometer magnetometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton welcomeBtn = (FloatingActionButton) findViewById(R.id.welcomeBtn);
        welcomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Welcome to the  IMU Application!", Snackbar.LENGTH_LONG).show();
            }
        });

        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        magnetometer = new Magnetometer(this);
        accelerometer.setListener(new MotionSensorListener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                TextView textView = (TextView) findViewById(R.id.accelerometerData);
                textView.setTypeface(null, Typeface.NORMAL);
                textView.setText("");
                textView.setTextSize(20);
                StringBuilder sb = new StringBuilder();
                sb
                        .append("Acceleration force along the x axis: ")
                        .append(Float.toString(tx))
                        .append(" m/(s^2)")
                        .append("\n")
                        .append("Acceleration force along the y axis: ")
                        .append(Float.toString(ty))
                        .append(" m/(s^2)")
                        .append("\n")
                        .append("Acceleration force along the z axis: ")
                        .append(Float.toString(tz))
                        .append(" m/(s^2)")
                        .append("\n");
                textView.append(sb.toString());
            }
        });
        gyroscope.setListener(new MotionSensorListener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                TextView textView = (TextView) findViewById(R.id.gyroscopeData);
                textView.setTypeface(null, Typeface.NORMAL);
                textView.setText("");
                textView.setTextSize(20);
                StringBuilder sb = new StringBuilder();
                sb
                        .append("Rate of rotation around the x axis: ")
                        .append(Float.toString(tx))
                        .append(" rad/s")
                        .append("\n")
                        .append("Rate of rotation around the y axis: ")
                        .append(Float.toString(ty))
                        .append(" rad/s")
                        .append("\n")
                        .append("Rate of rotation around the z axis: ")
                        .append(Float.toString(tz))
                        .append(" rad/s")
                        .append("\n");
                textView.append(sb.toString());
            }
        });
        magnetometer.setListener(new MotionSensorListener() {
            @Override
            public void onTranslation(float tx, float ty, float tz) {
                TextView textView = (TextView) findViewById(R.id.magnetometerData);
                textView.setTypeface(null, Typeface.NORMAL);
                textView.setText("");
                textView.setTextSize(18);
                StringBuilder sb = new StringBuilder();
                sb
                        .append("Geomagnetic field strength along the x axis: ")
                        .append(Float.toString(tx))
                        .append(" μT")
                        .append("\n")
                        .append("Geomagnetic field strength along the y axis: ")
                        .append(Float.toString(ty))
                        .append(" μT")
                        .append("\n")
                        .append("Geomagnetic field strength along the z axis: ")
                        .append(Float.toString(tz))
                        .append(" μT")
                        .append("\n");
                textView.append(sb.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
        magnetometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
        gyroscope.unregister();
        magnetometer.unregister();
    }

}