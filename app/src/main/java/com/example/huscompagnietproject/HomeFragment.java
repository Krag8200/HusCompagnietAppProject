package com.example.huscompagnietproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ProductAdapter.OnItemClickListener {

    RecyclerView productList;
    ProductAdapter productAdapter;
    Spinner dropdown;
    String[] items = new String[]{"No filter", "Wood", "Metal", "Other"};
    DatabaseReference dbReference;
    ArrayList<Product> productArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Instantiating and creating Recycler view
        dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/").child("Products");
        productList = rootView.findViewById(R.id.rv);
        productList.hasFixedSize();
        productList.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        // Instantiate ArrayList to show items in RV
        productArrayList = new ArrayList<>();

        // Instantiating and setting adapter
        productAdapter = new ProductAdapter(productArrayList);
        productList.setAdapter(productAdapter);

        // Call method readDatabase
        readDatabase(productArrayList);

        //Creating and setting adapter for dropdown
        dropdown = rootView.findViewById(R.id.filter_dropdown);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        // Instantiating category depending on what is selected from dropdown
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        // Instantiating and setting adapter
                        productAdapter = new ProductAdapter(productArrayList);
                        productList.setAdapter(productAdapter);
                        break;
                    case 1:
                        ArrayList<Product> woodList = new ArrayList<>();
                        for (Product product : productArrayList) {
                            if (product.getCategory().contains(items[1])) {
                                woodList.add(product);
                            }
                        }
                        // Instantiating and setting adapter
                        productAdapter = new ProductAdapter(woodList);
                        productList.setAdapter(productAdapter);
                        break;
                    case 2:
                        ArrayList<Product> metalList = new ArrayList<>();
                        for (Product product : productArrayList) {
                            if (product.getCategory().contains(items[2])) {
                                metalList.add(product);
                            }
                        }
                        // Instantiating and setting adapter
                        productAdapter = new ProductAdapter(metalList);
                        productList.setAdapter(productAdapter);
                        break;
                    case 3:
                        ArrayList<Product> otherList = new ArrayList<>();
                        for (Product product : productArrayList) {
                            if (product.getCategory().contains(items[3])) {
                                otherList.add(product);
                            }
                        }
                        // Instantiating and setting adapter
                        productAdapter = new ProductAdapter(otherList);
                        productList.setAdapter(productAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return rootView;
    }

    //Read data from database and loop through all entries
    public void readDatabase(ArrayList list) {

        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                        String title = snapshot1.child("Title").getValue(String.class);
                        String desc = snapshot1.child("Description").getValue(String.class);
                        int price = snapshot1.child("Price").getValue(Integer.class);
                        String cat = snapshot1.child("Category").getValue(String.class);
                        String user = snapshot1.child("Enlisted By User").getValue(String.class);

                        list.add(new Product(title, desc, price, cat, user));
                    }
                }
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getActivity(), SelectedItemActivity.class);
        intent.putExtra("productTitle", productArrayList.get(position).getTitle());
        intent.putExtra("productDesc", productArrayList.get(position).getDescription());
        intent.putExtra("productPrice", productArrayList.get(position).getPrice());


    }



/*    @Override
    public void onClick(Product product) {
        productAdapter.setOnClickListener(prd -> {
            Intent intent = new Intent(getActivity(), SelectedItemActivity.class);
            intent.putExtra("Product_title", prd.getTitle());
            intent.putExtra("Product_description", prd.getDescription());
            intent.putExtra("Product_price", prd.getPrice());
            startActivity(intent);
        });

    }*/

    }
