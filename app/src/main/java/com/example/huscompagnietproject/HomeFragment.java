package com.example.huscompagnietproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView productList;
    ProductAdapter productAdapter;

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
        products.add(new Products("Product1",R.drawable.ic_launcher_foreground));
        products.add(new Products("Product2",R.drawable.ic_launcher_foreground));

        productAdapter = new ProductAdapter(products);
        productList.setAdapter(productAdapter);

        return rootView;
    }

}