package com.example.huscompagnietproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.title.setText(products.get(position).getTitle());
        viewHolder.image.setImageResource(products.get(position).getImageId());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            image = itemView.findViewById(R.id.item_picture);
        }
    }

    private ArrayList<Products> products;

    public ProductAdapter() {

    }

    ProductAdapter(ArrayList<Products> products) {
        this.products = products;
    }


}
