package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set listener to entire layout
        ConstraintLayout entireLayout = findViewById(R.id.welcome);
        entireLayout.setOnClickListener(new WelcomeClickListener());
    }

    private class WelcomeClickListener implements View.OnClickListener {
        // continue to menu when any place on the screen is clicked
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
