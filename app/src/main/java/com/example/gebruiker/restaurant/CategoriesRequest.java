package com.example.gebruiker.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoriesRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Context context;
    public Callback activity;

    public interface Callback {
        void gotCategories(ArrayList<String> categories);
        void gotCategoriesError(String message);
    }

    // constructor
    public CategoriesRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        // pass error on to activity if request is unsuccessful
        String message = error.getMessage();
        activity.gotCategoriesError(message);
    }

    @Override
    public void onResponse(JSONObject response) {

        // create list of categories
        ArrayList<String> categories = new ArrayList();
        try {
            JSONArray jsonArray = response.getJSONArray("categories");

            for (int i = 0; i < jsonArray.length(); i++) {
                categories.add(jsonArray.getString(i));
            }
            activity.gotCategories(categories);
        }
        catch(JSONException jse){

        }
    }

    public void getCategories(Callback activity) {

        // add new JSON request to the queue, notify activity if successful
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/categories",null, this, this);
        queue.add(jsonObjectRequest);
    }
}
