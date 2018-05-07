package com.example.gebruiker.restaurant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

        lv = findViewById(R.id.list);
        lv.setOnItemClickListener(new CategoryClickListener());
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        CategoriesAdapter adapter = new CategoriesAdapter(this, R.layout.item_categories, categories);
        lv.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
    }

    // Click on category in list leads to corresponding menu, category being passed on in intent
    private class CategoryClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String category = (String) parent.getItemAtPosition(position);
            Log.d("jo", category);

            Intent intent = new Intent(CategoriesActivity.this, MenuActivity.class);
            intent.putExtra("category", category);
            startActivity(intent);
        }
    }


}
