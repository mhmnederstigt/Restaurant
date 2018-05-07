package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // retrieve category that was clicked
        Intent intent = getIntent();
        MenuItem retrievedCategory = (MenuItem) intent.getSerializableExtra("clickedCategory");

        // display layout according to clicked category item
        ImageView image = (ImageView) findViewById(R.id.image);

        TextView name = findViewById(R.id.name);
        name.setText(retrievedCategory.getName());

        TextView description = findViewById(R.id.description);
        description.setText(retrievedCategory.getDescription());

        TextView price = findViewById(R.id.price);
        String priceText = "€ " + String.valueOf(retrievedCategory.getPrice());
        price.setText(priceText);

    }
}
