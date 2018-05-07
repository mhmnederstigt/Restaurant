package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity implements MenuRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // retrieve requested category from intent
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        // create new request for menu corresponding to category
        MenuRequest request = new MenuRequest(this, category);
        request.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {

        // when menu items are loaded, use adapter to display them in listview
        MenuAdapter adapter = new MenuAdapter(this, R.layout.item_menu, menuItems);

        ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);

        // also set a listener to the items in listview
        lv.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {

        // when categories are not loaded successfully, print error
        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
    }

    // click on item leads to detailed activity, info of menu item being passed on in intent
    private class ItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            MenuItem clickedCategory = (MenuItem) parent.getItemAtPosition(position);

            Intent intent = new Intent(MenuActivity.this, MenuItemActivity.class);
            intent.putExtra("clickedCategory", clickedCategory);
            startActivity(intent);
        }
    }
}
