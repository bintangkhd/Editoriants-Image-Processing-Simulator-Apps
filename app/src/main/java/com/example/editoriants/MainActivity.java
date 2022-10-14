package com.example.editoriants;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnNegative;
    private Button btnGrayscale;
    private Button btnBrightening;
    private Button btnArithmetic;
    private Button btnBoolean;
    private Button btnGeometry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNegative = (Button) findViewById(R.id.button_negative);

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NegativePage.class);
                startActivity(intent);
            }
        });

        btnGrayscale = (Button)findViewById(R.id.button_grayscale);

        btnGrayscale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GrayscalePage.class);
                startActivity(intent);
            }
        });

        btnBrightening = (Button)findViewById(R.id.button_brightening);

        btnBrightening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BrighteningPage.class);
                startActivity(intent);
            }
        });

        btnArithmetic = (Button) findViewById(R.id.button_arithmetic);

        btnArithmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArithmeticPage.class);
                startActivity(intent);
            }
        });

        btnBoolean = (Button) findViewById(R.id.button_boolean);

        btnBoolean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BooleanPage.class);
                startActivity(intent);
            }
        });

        btnGeometry = (Button) findViewById(R.id.button_geometry);

        btnGeometry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GeometryPage.class);
                startActivity(intent);
            }
        });

    }


}