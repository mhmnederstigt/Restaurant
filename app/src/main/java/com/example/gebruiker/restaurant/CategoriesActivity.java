package com.example.gebruiker.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CategoriesActivity extends AppCompatActivity implements CategoriesRequest.Callback {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        CategoriesRequest request = new CategoriesRequest(this);
        request.getCategories(this);

       ListView lv = findViewById(R.id.list);
    }

    @Override
    public void gotCategories(ArrayList<String> categories) {
        Log.d("catact", String.valueOf(categories));
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_categories, R.id.list, categories)  ;
        lv.setAdapter(adapter);
    }

    @Override
    public void gotCategoriesError(String message) {
        Log.d("hi", "error");
    }
}
