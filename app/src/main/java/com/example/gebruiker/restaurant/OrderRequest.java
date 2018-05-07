package com.example.gebruiker.restaurant;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class OrderRequest implements Response.Listener<JSONObject>, Response.ErrorListener {
    public Context context;
    public Callback activity;

    public interface Callback {
        void gotConfirmation(int deliveryTime);
        void gotConfirmationError(String message);
    }

    // constructor
    public OrderRequest(Context context) {
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String message = error.getMessage();
        activity.gotConfirmationError(message);
    }

    @Override
    public void onResponse(JSONObject response) {

        try {
            int deliveryTime = response.getInt("preparation_time");
            Log.d("response", String.valueOf(deliveryTime));
            activity.gotConfirmation(deliveryTime);
        }

        catch(JSONException jse){
            jse.printStackTrace();
        }
    }

    public void getConfirmation(Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,"https://resto.mprog.nl/order",null, this, this);
        queue.add(jsonObjectRequest);
    }
}
