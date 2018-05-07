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

public class MenuRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Context context;
    private String requestedCategory;
    public MenuRequest.Callback activity;

    public interface Callback {
        void gotMenu(ArrayList<MenuItem> menuItems);
        void gotMenuError(String message);
    }

    // constructor
    public MenuRequest(Context context, String category) {
        this.context = context;
        requestedCategory = category;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotMenuError(message);
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            ArrayList<MenuItem> menuItems = new ArrayList<>();
            JSONArray jsonArray = response.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // retrieve category per JSON item
                String category = jsonObject.getString("category");

                // if category has to be in list, include in list
                if (requestedCategory.equals(category)) {
                    MenuItem item = new MenuItem(jsonObject.getString("name"), jsonObject.getString("description"), jsonObject.getString("image_url"), jsonObject.getInt("price"), category);
                    menuItems.add(item);
                    activity.gotMenu(menuItems);
                }
            }
        }
        catch(JSONException jse){
            jse.printStackTrace();
        }
    }

    public void getMenu(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://resto.mprog.nl/menu",null, this, this);
        queue.add(jsonObjectRequest);
    }
}
