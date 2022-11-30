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

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends Fragment {

    DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private Button logoutButton;
    private Button enlistButton;
    private BottomNavigationView bottomNavigationView;

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

        mAuth = FirebaseAuth.getInstance();
        logoutButton = profileFragmentView.findViewById(R.id.logoutButton);
        enlistButton = profileFragmentView.findViewById(R.id.enlist_button);
        bottomNavigationView = profileFragmentView.findViewById(R.id.bottomNavigationView);
        dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/");


        logoutButton.setOnClickListener(view -> {
            logoutUser();
        });

        enlistButton.setOnClickListener(view -> {
            //TODO: Create event for enlisting - potentially new fragment

            replaceFragment(new EnlistFragment());

/*            //TEST EVENT
            dbReference.child("message");
            dbReference.addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // Sending data to Firebase Realtime Database using email is unique ID
                    dbReference.child("message").setValue("uh ih uh ahhh");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });*/

        });

        return profileFragmentView;
    }

    public void logoutUser() {
        mAuth.signOut();
        Toast.makeText(getActivity(), "Logged out successfully!", Toast.LENGTH_SHORT).show();
        replaceFragment(new HomeFragment());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }


}