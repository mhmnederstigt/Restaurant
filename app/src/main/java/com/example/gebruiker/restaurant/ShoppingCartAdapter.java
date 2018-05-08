package com.example.gebruiker.restaurant;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShoppingCartAdapter extends ArrayAdapter<MenuItem> {

    private ArrayList<MenuItem> shoppingCart;

    public ShoppingCartAdapter(@NonNull Context context, int resource, @NonNull ArrayList<MenuItem> objects) {
        super(context, resource, objects);
        shoppingCart = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_shoppingcart, parent, false);
        }

        // display info per list item according to item_categories.xml
        TextView name = convertView.findViewById(R.id.name);
        name.setText(shoppingCart.get(position).getName());

        TextView price = convertView.findViewById(R.id.price);
        String priceText = "€ " + String.valueOf(shoppingCart.get(position).getPrice());
        price.setText(priceText);

        ImageView image = convertView.findViewById(R.id.image);
        Picasso.get().load(shoppingCart.get(position).getImageUrl()).resize(200, 200)
                .centerCrop().into(image);

        return convertView;
    }
}


