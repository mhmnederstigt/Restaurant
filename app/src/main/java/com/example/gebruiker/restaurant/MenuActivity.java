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

        // Retrieve requested category from intent
        Intent intent = getIntent();
        String category = (String) intent.getStringExtra("category");
        Log.d("intent: category", category);

        MenuRequest request = new MenuRequest(this, category);
        request.getMenu(this);
    }

    @Override
    public void gotMenu(ArrayList<MenuItem> menuItems) {
        MenuAdapter adapter = new MenuAdapter(this, R.layout.item_menu, menuItems);
        ListView lv = findViewById(R.id.list);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new ItemClickListener());
    }

    @Override
    public void gotMenuError(String message) {
        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
    }

    // Click on item leads to detailed activity, info of friend being passed on in intent
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
