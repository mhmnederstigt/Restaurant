package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    ArrayList<MenuItem> shoppingCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // retrieve requested category from intent
        Intent intent = getIntent();
        shoppingCart = (ArrayList<MenuItem>) intent.getSerializableExtra("shoppingCart");

        // when menu items are loaded, use adapter to display them in listview
        ShoppingCartAdapter adapter = new ShoppingCartAdapter(this, R.layout.item_shoppingcart, shoppingCart);

        ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);

        // add listeners to buttons
        Button proceed = findViewById(R.id.proceed);
        proceed.setOnClickListener(new ProceedClickListener());

        Button order = findViewById(R.id.order);
        order.setOnClickListener(new OrderClickListener());

        // calculate total price of items and display
        int totalPrice = 0;

        for (int i=0; i < shoppingCart.size(); i++) {
            int price = shoppingCart.get(i).getPrice();
            totalPrice += price;
        }

        TextView totalPriceDisplay = findViewById(R.id.totalprice);
        totalPriceDisplay.setText("Total price: €" + totalPrice);
    }

    public class ProceedClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // show shopping cart and pass shopping cart on in intent
            Intent intent = new Intent(ShoppingCartActivity.this, CategoriesActivity.class);
            intent.putExtra("shoppingCart", shoppingCart);
            startActivity(intent);
            finish();
        }
    }

    public class OrderClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            Intent intent = new Intent(ShoppingCartActivity.this, OrderedActivity.class);
            intent.putExtra("shoppingCart", shoppingCart);
            startActivity(intent);
            finish();
        }
    }
}
