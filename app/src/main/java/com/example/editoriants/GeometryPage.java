package com.example.editoriants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class GeometryPage extends Activity {

    private Button btnTranslation;
    private Button btnRotation;
    private Button btnFlip;
    private Button btnZoom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geometry_menu_page);

        btnTranslation = (Button) findViewById(R.id.buttonTranslation);

        btnTranslation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeometryPage.this, TranslationPage.class);
                startActivity(intent);
            }
        });

        btnRotation = (Button) findViewById(R.id.buttonRotation);

        btnRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeometryPage.this, RotationPage.class);
                startActivity(intent);
            }
        });

        btnFlip = (Button) findViewById(R.id.buttonFlip);

        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeometryPage.this, FlipPage.class);
                startActivity(intent);
            }
        });

        btnZoom = (Button) findViewById(R.id.buttonZoom);

        btnZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GeometryPage.this, ZoomPage.class);
                startActivity(intent);
            }
        });
    }
}
