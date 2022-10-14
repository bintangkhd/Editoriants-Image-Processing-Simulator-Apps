package com.example.editoriants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class BooleanPage extends Activity {

    private Button btnAND;
    private Button btnOR;
    private Button btnXOR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boolean_menu_page);

        btnAND =(Button) findViewById(R.id.buttonAND);

        btnAND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BooleanPage.this, AndPage.class);
                startActivity(intent);
            }
        });

        btnOR =(Button) findViewById(R.id.buttonOR);

        btnOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BooleanPage.this, OrPage.class);
                startActivity(intent);
            }
        });

        btnXOR =(Button) findViewById(R.id.buttonXOR);

        btnXOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BooleanPage.this, XorPage.class);
                startActivity(intent);
            }
        });
    }
}
