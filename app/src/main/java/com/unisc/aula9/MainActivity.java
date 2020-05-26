package com.unisc.aula9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity  extends AppCompatActivity implements SensorEventListener {

    private TextView a, b, c, d, e, f, g, h, i;
    SensorManager sensorM;
    Sensor sensor;
    LinearLayout containerCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Exercicio aula 9");

        a = findViewById(R.id.txt1);
        b = findViewById(R.id.txt2);
        c = findViewById(R.id.txt3);
        d = findViewById(R.id.txt4);
        e = findViewById(R.id.txt5);
        f = findViewById(R.id.txt6);
        g = findViewById(R.id.txt7);
        h = findViewById(R.id.txt8);
        i = findViewById(R.id.txt9);
        containerCor = findViewById(R.id.container);

        sensorM = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorM.registerListener(this, sensorM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(this, sensorM.getDefaultSensor(Sensor.TYPE_PROXIMITY), SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(this, sensorM.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(this, sensorM.getDefaultSensor(Sensor.TYPE_PRESSURE), SensorManager.SENSOR_DELAY_NORMAL);
        sensorM.registerListener(this, sensorM.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            a.setText("Accel X = " + sensorEvent.values[0]);
            b.setText("Accel Y = " + sensorEvent.values[1]);
            c.setText("Accel Z = " + sensorEvent.values[2]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            d.setText("Proximidade = " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT) {
            e.setText("Luz = " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PRESSURE) {
            f.setText("Pressão = " + sensorEvent.values[0]);
        }
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
            g.setText("Orientação X = " + sensorEvent.values[0]);
            h.setText("Orientação Y = " + sensorEvent.values[1]);
            i.setText("Orientação Z = " + sensorEvent.values[2]);

            float x = sensorEvent.values[0];
            float z = sensorEvent.values[2];

            if ((z > 1.2 && z < 1.8) || (z < -1.2 && z > -1.8)) {
                containerCor.setBackgroundColor(Color.GREEN); //landscape
            } else {
                if ((z > -0.8 && z < 0.2) || (z < -2.84 && z > -3.44)) {
                    containerCor.setBackgroundColor(Color.BLUE); //portrait
                }
            }

            if (x > 1.27 && x < 1.87) {
                //finish(); fecha a activity
                System.exit(0);
            } else {
                if (x < -1.27 && x > -1.87) {
                    containerCor.setBackgroundColor(Color.RED);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorM.unregisterListener(this);
    }

    public void gpsClick(View view) {
        startActivity(new Intent(this, Aula9GpsActivity.class));
    }
}
