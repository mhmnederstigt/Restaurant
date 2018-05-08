package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class MenuItemActivity extends AppCompatActivity {
    ArrayList<MenuItem> shoppingCart = new ArrayList<>();
    MenuItem retrievedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        // check if items from shopping cart are saved in intent
        Intent intent = getIntent();
        ArrayList<MenuItem> retrievedCart = (ArrayList<MenuItem>) intent.getSerializableExtra("shoppingCart");

        if (retrievedCart != null){
            shoppingCart = retrievedCart;
        }

        // and retrieve category that was clicked
        retrievedCategory = (MenuItem) intent.getSerializableExtra("clickedCategory");

        // display layout according to clicked category item
        ImageView image = findViewById(R.id.image);
        Picasso.get().load(retrievedCategory.getImageUrl()).resize(400, 400)
                .centerCrop().into(image);

        TextView name = findViewById(R.id.name);
        name.setText(retrievedCategory.getName());

        TextView description = findViewById(R.id.description);
        description.setText(retrievedCategory.getDescription());

        TextView price = findViewById(R.id.price);
        String priceText = "€ " + String.valueOf(retrievedCategory.getPrice());
        price.setText(priceText);

        Button order = findViewById(R.id.add);
        order.setOnClickListener(new OrderClickListener());
    }

    private class OrderClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            // add menu item to shopping cart
            shoppingCart.add(retrievedCategory);

            // show shopping cart and pass shopping cart on in intent
            Intent intent = new Intent(MenuItemActivity.this, ShoppingCartActivity.class);
            intent.putExtra("shoppingCart", shoppingCart);
            startActivityForResult(intent, 1);
        }

    }

    @Override
    // pass on shopping cart when returning to last activity on stack
    public void finish() {

        // prepare
        Intent intent = new Intent();
        intent.putExtra("shoppingCart", shoppingCart);

        // activity finished ok, return shopping cart
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    // on return, retrieve shopping cart
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            if (data.hasExtra("shoppingCart")) {
                shoppingCart =  (ArrayList<MenuItem>) data.getExtras().getSerializable("shoppingCart");
            }
        }

    }

}
