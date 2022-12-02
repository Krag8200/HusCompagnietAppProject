package com.example.huscompagnietproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SelectedItemActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView price;
    private Button buyButton;
    private Button addFavouriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        title = findViewById(R.id.selected_item_title);
        description = findViewById(R.id.selected_item_description);
        price = findViewById(R.id.selected_item_price);
        buyButton = findViewById(R.id.buy_button);
        addFavouriteButton = findViewById(R.id.add_favourite_button);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("productTitle")) {
            String bundleTitle = bundle.getString("productTitle");
            String bundleDesc = bundle.getString("productDesc");
            int bundlePrice = bundle.getInt("productPrice");
            price.setText((int) bundlePrice);
            description.setText(bundleDesc);
            title.setText(bundleTitle);
        }
    }
}