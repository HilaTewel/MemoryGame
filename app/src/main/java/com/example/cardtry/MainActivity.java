package com.example.cardtry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easy = findViewById(R.id.gotoeasytry);
        Button medium = findViewById(R.id.gotomediumtry);
        Button hard = findViewById(R.id.gotohardtry);

        Button worlds = findViewById(R.id.worldsBtn);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpaceLevelEasy.class);
                startActivity(intent);
            }
        });
        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpaceLevelMedium.class);
                startActivity(intent);
            }
        });
        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SpaceLevelHard.class);
                startActivity(intent);
            }
        });

        worlds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WorldActivity.class);
                startActivity(intent);
            }
        });

    }
}
