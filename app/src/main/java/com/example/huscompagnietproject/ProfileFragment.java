package com.example.huscompagnietproject;

import android.os.Bundle;

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

public class ProfileFragment extends Fragment {

    private FirebaseAuth mAuth;
    private Button logoutButton;
    private BottomNavigationView bottomNavigationView;
    // private TextView continueNoLogin;

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
        bottomNavigationView = profileFragmentView.findViewById(R.id.bottomNavigationView);
        // continueNoLogin = profileFragmentView.findViewById(R.id.continue_no_login);

        logoutButton.setOnClickListener(view -> {
            logoutUser();
        });

/*        continueNoLogin.setOnClickListener(view -> {
            replaceFragment(new HomeFragment());
        });     */

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