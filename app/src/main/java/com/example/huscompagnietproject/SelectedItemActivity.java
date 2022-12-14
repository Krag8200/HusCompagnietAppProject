package com.example.huscompagnietproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelectedItemActivity extends AppCompatActivity {

    private TextView title;
    private TextView description;
    private TextView price;
    private int productId;
    private Button buyButton;
    private EditText commentEditText;
    private Button addCommentButton;
    private FirebaseAuth mAuth;
    private DatabaseReference dbReference;
    RecyclerView commentListRv;
    CommentAdapter commentAdapter;
    ArrayList<Comment> comments;
    long maxId=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_item);

        // Instantiating
        title = findViewById(R.id.selected_item_title);
        description = findViewById(R.id.selected_item_description);
        price = findViewById(R.id.selected_item_price);
        buyButton = findViewById(R.id.buy_button);
        commentEditText = findViewById(R.id.comment_textField);
        addCommentButton = findViewById(R.id.add_comment_button);

        mAuth = FirebaseAuth.getInstance();
        dbReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://huscompagnietproject-default-rtdb.europe-west1.firebasedatabase.app/");


        commentListRv = findViewById(R.id.comment_section_rv);
        commentListRv.hasFixedSize();
        commentListRv.setLayoutManager(new LinearLayoutManager(this));

        comments = new ArrayList<>();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null && bundle.containsKey("productTitle")) {
            String bundleTitle = bundle.getString("productTitle");
            String bundleDesc = bundle.getString("productDesc");
            double bundlePrice = (double) bundle.get("productPrice");
            int bundleId = (int) bundle.get("productId");
            price.setText("DKK " + bundlePrice);
            description.setText(bundleDesc);
            title.setText(bundleTitle);
            productId = bundleId;
        }

        commentAdapter = new CommentAdapter(comments);
        commentListRv.setAdapter(commentAdapter);

        readDatabase();

        // Get current amount of enlisted items in DB
        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child("Products").child(String.valueOf(productId)).child("Comments").exists()) {
                    maxId = (snapshot.child("Products").child(String.valueOf(productId)).child("Comments").getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        addCommentButton.setOnClickListener(view -> {

            if (mAuth.getCurrentUser() != null) {

                String user = mAuth.getCurrentUser().getEmail();
                String commentText = commentEditText.getText().toString();

                if (commentText.isEmpty()) {
                    Toast.makeText(this, "Please enter a comment", Toast.LENGTH_SHORT).show();
                } else {
                    dbReference.child("Products").child(String.valueOf(productId)).child("Comments").child(String.valueOf(maxId + 1)).child("User").setValue(user);
                    dbReference.child("Products").child(String.valueOf(productId)).child("Comments").child(String.valueOf(maxId + 1)).child("Comment").setValue(commentText);

                    Toast.makeText(this, "Comment was added!", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(this, "You must login to make a comment!", Toast.LENGTH_SHORT).show();
            }
        });

        // Has not been implemented with actual payment
        buyButton.setOnClickListener(view -> {
            if (mAuth.getCurrentUser() != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Purchase product?");
                alertDialog.setMessage("Are you sure you want to purchase " + title.getText() + " for " + price.getText() + "?");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "YES",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(SelectedItemActivity.this, "Product has been purchased!", Toast.LENGTH_SHORT).show();
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "CANCEL",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                alertDialog.show();
            } else {
                Toast.makeText(this, "You must login to buy a product!", Toast.LENGTH_SHORT).show();
            }
        });

    }
    // Read database and instantiate adapter
    // NOTE: Database is read correctly read from DB and inserted into ArrayList, however ArrayList is not enlisting items to RecyclerView
    // - Same procedure works within HomeFragment
    private void readDatabase() {
        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot snapshot1 : snapshot.child("Products").child(String.valueOf(productId)).child("Comments").getChildren()) {

                        String commentUser = snapshot1.child("User").getValue(String.class);
                        String commentText = snapshot1.child("Comment").getValue(String.class);

                        comments.add(new Comment(commentUser, commentText));
                    }
                }
                commentAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}