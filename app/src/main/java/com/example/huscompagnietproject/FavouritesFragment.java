package com.example.huscompagnietproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class FavouritesFragment extends Fragment {

    RecyclerView favouriteProductList;
    ProductAdapter productAdapter;
    Spinner dropdown;
    String[] items = new String[]{"No filter", "Wood", "Metal", "Other"};

    public FavouritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Instantiating and creating Recycler view
        favouriteProductList = rootView.findViewById(R.id.rv_favourite);
        favouriteProductList.hasFixedSize();
        favouriteProductList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        // Dummy data - TODO: Populate from DB
        ArrayList<Products> products = new ArrayList<>();
        products.add(new Products("FavouriteProduct1","Description1", 100, "Wood", "testuser"));
        products.add(new Products("FavouriteProduct1","Description1", 100, "Wood", "testuser"));
        products.add(new Products("FavouriteProduct1","Description1", 100, "Wood", "testuser"));

        // Instantiating and setting adapter
        dropdown = rootView.findViewById(R.id.favourite_filter_dropdown);
        productAdapter = new ProductAdapter(products, (ProductAdapter.SelectedItem) this);
        favouriteProductList.setAdapter(productAdapter);

        //Creating and setting adapter for dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        // Instantiating category depending on what is selected from dropdown
        // TODO: Implement functionality for dropdown
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        // Whatever you want to happen when the first item gets selected
                        break;
                    case 1:
                        // Whatever you want to happen when the second item gets selected
                        break;
                    case 2:
                        // Whatever you want to happen when the thrid item gets selected
                        break;
                    case 3:
                        // 4th item selected
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }
}