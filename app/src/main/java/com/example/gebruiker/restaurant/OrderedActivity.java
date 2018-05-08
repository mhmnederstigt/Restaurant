package com.example.gebruiker.restaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
        deliveryTime.setText("The estimated time of arrival is " + String.valueOf(time) + " minutes.");

    }

    @Override
    public void gotConfirmationError(String message) {

        // when categories are not loaded succesfully, print error
        Toast.makeText(getApplicationContext(), "Error!", Toast.LENGTH_SHORT).show();
    }
}
