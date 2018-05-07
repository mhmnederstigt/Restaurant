package com.example.gebruiker.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class OrderedActivity extends AppCompatActivity implements OrderRequest.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordered);

        OrderRequest request = new OrderRequest(this);
        request.getConfirmation(this);


    }

    @Override
    public void gotConfirmation(int time) {
        TextView deliveryTime = findViewById(R.id.delivery);
        deliveryTime.setText(String.valueOf(time));

    }

    @Override
    public void gotConfirmationError(String message) {

    }
}
