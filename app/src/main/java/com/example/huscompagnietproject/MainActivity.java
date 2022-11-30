package com.example.huscompagnietproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.huscompagnietproject.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiating
        mAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Fragments for navigation bar
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.browse:
                    // Open HomeFragment
                    replaceFragment(new HomeFragment());
                    bottomNavigationView.setSelectedItemId(R.id.browse);
                    break;
                case R.id.favourites:
                    // Open FavouritesFragment
                    replaceFragment(new FavouritesFragment());
                    bottomNavigationView.setSelectedItemId(R.id.favourites);
                    break;
                case R.id.profile:
                    // Open ProfileFragment or LoginActivity depending on whether user is logged in
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if(currentUser != null){
                        replaceFragment(new ProfileFragment());
                    } else {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    }
                    bottomNavigationView.setSelectedItemId(R.id.profile);
                    break;
            }

            return true;

        });

    }

    // Replacing fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flFragment, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}

