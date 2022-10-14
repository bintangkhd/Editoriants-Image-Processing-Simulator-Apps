package com.example.editoriants;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class ArithmeticPage extends Activity {

    private Button btnAddition;
    private Button btnSubstraction;
    private Button btnMultiplication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arithmetic_menu_page);

        btnAddition = (Button) findViewById(R.id.buttonAddition);

        btnAddition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArithmeticPage.this, AdditionPage.class);
                startActivity(intent);
            }
        });

        btnSubstraction = (Button) findViewById(R.id.buttonSubstraction);

        btnSubstraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArithmeticPage.this, SubstractionPage.class);
                startActivity(intent);
            }
        });

        btnMultiplication = (Button) findViewById(R.id.buttonMultiplication);

        btnMultiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArithmeticPage.this, MultiplicationPage.class);
                startActivity(intent);
            }
        });

    }
}
