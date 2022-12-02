package com.example.huscompagnietproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends  RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private OnItemClickListener selectedItem;

    // Inflate layout for RecyclerView
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
        viewHolder.description.setText(products.get(position).getDescription());
        viewHolder.price.setText(String.valueOf(products.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public interface OnItemClickListener {
        void onClick(Products product);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView description;
        private final TextView price;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_description);
            price = itemView.findViewById(R.id.item_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedItem.onClick(products.get(getAdapterPosition()));
                }
            });
        }
    }

    private ArrayList<Products> products;

    public ProductAdapter() {

    }

    ProductAdapter(ArrayList<Products> products) {
        this.products = products;
    }

    public void setOnClickListener(OnItemClickListener listener){
        selectedItem = listener;
    }


}
