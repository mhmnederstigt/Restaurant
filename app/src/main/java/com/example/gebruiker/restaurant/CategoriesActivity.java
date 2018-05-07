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

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        // retrieve categories by creating a new request
        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        // define listview and set listener to listview's items
        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new CategoryClickListener());
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {

        // when categories are loaded, use adapter to display them in listview
        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.item_categories, categories);
        lv.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {

        // when categories are not loaded succesfully, print error
        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
    }

    // Click on category in list leads to corresponding menu, the category is passed on in intent
    private class CategoryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }
}
