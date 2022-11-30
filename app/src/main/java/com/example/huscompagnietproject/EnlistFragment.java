package com.example.huscompagnietproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EnlistFragment extends Fragment {

    private TextInputEditText titleInput;
    private TextInputEditText descriptionInput;
    private TextInputEditText priceInput;
    private Button enlistItem;
    private String category;
    private String enlistedByUser;
    private FirebaseAuth mAuth;
    Spinner dropdown;
    String[] items = new String[]{"No category", "Wood", "Metal", "Other"};
    DatabaseReference dbReference;
    Products products;
    long maxId=0;

    public EnlistFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View enlistFragmentView = inflater.inflate(R.layout.fragment_enlist, container, false);

        // Instantiating
        titleInput = enlistFragmentView.findViewById(R.id.title_input);
        descriptionInput = enlistFragmentView.findViewById(R.id.description_input);
        priceInput = enlistFragmentView.findViewById(R.id.price_input);
        enlistItem = enlistFragmentView.findViewById(R.id.submit_item_button);
        dropdown = enlistFragmentView.findViewById(R.id.select_category_dropdown);
        dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/");
        mAuth = FirebaseAuth.getInstance();
        enlistedByUser = mAuth.getCurrentUser().toString();

        // Get current amount of enlisted items in DB
        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Products").exists()) {
                    maxId = (snapshot.child("Products").getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Creating and setting adapter for dropdown
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        // Instantiating category depending on what is selected from dropdown
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case 0:
                        category = items[0];
                        break;
                    case 1:
                        category = items[1];
                        break;
                    case 2:
                        category = items[2];
                        break;
                    case 3:
                        category = items[4];
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Creating DB entry
        enlistItem.setOnClickListener(view -> {

            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            double price = Double.parseDouble(priceInput.getText().toString());
            products = new Products(title, description, price, category, enlistedByUser);

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Title").setValue(title);
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Description").setValue(description);
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Price").setValue(price);
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Category").setValue(category);
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Enlisted By User").setValue(enlistedByUser);

                Toast.makeText(getActivity(), "Product has been enlisted!", Toast.LENGTH_LONG).show();

                replaceFragment(new ProfileFragment());
            }
        });

        return enlistFragmentView;
    }

    // Replacing fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}