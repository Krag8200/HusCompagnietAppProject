package com.example.huscompagnietproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private Button logoutButton;
    private Button enlistButton;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView userEnlistedItems;
    private TextView userEmail;
    UserProductAdapter userProductAdapter;
    ArrayList<Product> products;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View profileFragmentView = inflater.inflate(R.layout.fragment_profile, container, false);

        // Instantiating
        mAuth = FirebaseAuth.getInstance();
        logoutButton = profileFragmentView.findViewById(R.id.logoutButton);
        enlistButton = profileFragmentView.findViewById(R.id.enlist_button);
        bottomNavigationView = profileFragmentView.findViewById(R.id.bottomNavigationView);
        userEmail = profileFragmentView.findViewById(R.id.user_email);
        dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/").child("Products");
        products = new ArrayList<>();

        // Displaying user email
        userEmail.setText(mAuth.getCurrentUser().getEmail());

        // Instantiating and creating Recycler view
        userEnlistedItems = profileFragmentView.findViewById(R.id.profile_rv);
        userEnlistedItems.hasFixedSize();
        userEnlistedItems.setLayoutManager(new LinearLayoutManager(profileFragmentView.getContext()));

        // Read through DB and show items enlisted by current user
        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                products.clear();
                if (snapshot.exists()) {

                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                        String title = snapshot1.child("Title").getValue(String.class);
                        String desc = snapshot1.child("Description").getValue(String.class);
                        int price = snapshot1.child("Price").getValue(Integer.class);
                        String cat = snapshot1.child("Category").getValue(String.class);
                        String user = snapshot1.child("Enlisted By User").getValue(String.class);
                        int productId = Integer.parseInt(snapshot1.getKey());


                        if (user.equals(userEmail.getText())) {
                            products.add(new Product(title, desc, price, cat, user, productId));
                        }
                    }
                }
                userProductAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Instantiating and setting adapter
        userProductAdapter = new UserProductAdapter(products);
        userEnlistedItems.setAdapter(userProductAdapter);

        logoutButton.setOnClickListener(view -> {
            logoutUser();
        });

        // Open EnlistFragment to enlist new item
        enlistButton.setOnClickListener(view -> {
            replaceFragment(new EnlistFragment());
        });

        return profileFragmentView;
    }

    // Logout the user and return to HomeFragment
    public void logoutUser() {
        mAuth.signOut();
        Toast.makeText(getActivity(), "Logged out successfully!", Toast.LENGTH_SHORT).show();
        replaceFragment(new HomeFragment());
    }

    // Replace fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }

}