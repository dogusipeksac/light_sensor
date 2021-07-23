package com.example.android_hardware_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Sensor sensor;
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,sensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
    MediaPlayer mp;
    boolean isRuning=false;
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.values[0]>40 && isRuning==false){
            isRuning=true;
            mp=new MediaPlayer();
            try {
                mp.setDataSource("https://radyonet.net/ece-mumay-peri");
                mp.prepare();
                mp.start();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}