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

public class HomeFragment extends Fragment {

    RecyclerView productList;
    ProductAdapter productAdapter;
    Spinner dropdown;
    String[] items = new String[]{"No filter", "Wood", "Metal", "Other"};

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        productList = rootView.findViewById(R.id.rv);
        productList.hasFixedSize();
        productList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        ArrayList<Products> products = new ArrayList<>();
        products.add(new Products("FavouriteProduct1","Description1", 100));
        products.add(new Products("FavouriteProduct1","Description1", 100));
        products.add(new Products("FavouriteProduct1","Description1", 100));
        products.add(new Products("FavouriteProduct1","Description1", 100));


        productAdapter = new ProductAdapter(products);
        productList.setAdapter(productAdapter);

        //get the spinner from the xml.
        dropdown = rootView.findViewById(R.id.filter_dropdown);
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);

        //set the spinners adapter to the previously created one.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

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