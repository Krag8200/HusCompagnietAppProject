package com.example.huscompagnietproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
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

        titleInput = enlistFragmentView.findViewById(R.id.title_input);
        descriptionInput = enlistFragmentView.findViewById(R.id.description_input);
        priceInput = enlistFragmentView.findViewById(R.id.price_input);
        enlistItem = enlistFragmentView.findViewById(R.id.submit_item_button);
        dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/");

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


        enlistItem.setOnClickListener(view -> {

            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            double price = Double.parseDouble(priceInput.getText().toString());
            products = new Products(title, description, price);

            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Title").setValue(title);
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Description").setValue(description);
                dbReference.child("Products").child(String.valueOf(maxId + 1)).child("Price").setValue(price);

                Toast.makeText(getActivity(), "Product has been enlisted!", Toast.LENGTH_LONG).show();

                replaceFragment(new ProfileFragment());
            }
        });

        return enlistFragmentView;
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }
}