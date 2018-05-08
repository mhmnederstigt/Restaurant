package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    ArrayList<MenuItem> shoppingCart;
    Intent data;
    ShoppingCartAdapter adapter;
    ListView lv;
    TextView totalPriceDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        // retrieve requested category from intent
        Intent intent = getIntent();
        shoppingCart = (ArrayList<MenuItem>) intent.getSerializableExtra("shoppingCart");

        // when menu items are loaded, use adapter to display them in listview
        adapter = new ShoppingCartAdapter(this, R.layout.item_shoppingcart, shoppingCart);

        lv = findViewById(R.id.list);
        lv.setAdapter(adapter);

        // set listeners to buttons
        Button proceed = findViewById(R.id.proceed);
        proceed.setOnClickListener(new ProceedClickListener());

        Button empty = findViewById(R.id.empty);
        empty.setOnClickListener(new EmptyClickListener());

        Button order = findViewById(R.id.order);
        order.setOnClickListener(new OrderClickListener());

        // calculate total price of items and display
        int totalPrice = 0;

        for (int i=0; i < shoppingCart.size(); i++) {
            int price = shoppingCart.get(i).getPrice();
            totalPrice += price;
        }

        totalPriceDisplay = findViewById(R.id.totalprice);
        totalPriceDisplay.setText("Total price: €" + totalPrice);
    }

    public class ProceedClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // show shopping cart and pass shopping cart on in intent
            Intent intent = new Intent(ShoppingCartActivity.this, CategoriesActivity.class);
            intent.putExtra("shoppingCart", shoppingCart);
            startActivityForResult(intent, 1);

        }
    }

    public class EmptyClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            emptyCart();
        }
    }

    public class OrderClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            data = new Intent(ShoppingCartActivity.this, OrderedActivity.class);
            data.putExtra("shoppingCart", shoppingCart);
            startActivityForResult(data, 0);
        }
    }

    @Override
    // pass on shopping cart when returning to last activity on stack
    public void finish() {

        // prepare
        Intent data = new Intent();
        data.putExtra("shoppingCart", shoppingCart);

        // activity finished ok, return shopping cart
        setResult(RESULT_OK, data);
        super.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == 0) {
           emptyCart();
        }
    }

    protected void emptyCart() {
        // empty shopping cart
        shoppingCart = new ArrayList<>();

        // update UI
        adapter = new ShoppingCartAdapter(ShoppingCartActivity.this, R.layout.item_shoppingcart, shoppingCart);
        lv.setAdapter(adapter);
        totalPriceDisplay.setText("Shopping cart is empty");
    }
}
