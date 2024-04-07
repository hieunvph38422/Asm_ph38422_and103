package com.example.asm_ph38422_and103.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_ph38422_and103.R;
import com.example.asm_ph38422_and103.databinding.ItemCartBinding;
import com.example.asm_ph38422_and103.model.CartItem;
import com.example.asm_ph38422_and103.view.CartManager;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private ArrayList<CartItem> cartItems;
    private CartItemClick cartItemClick;

    public CartAdapter(Context context, ArrayList<CartItem> cartItems, CartItemClick cartItemClick) {
        this.context = context;
        this.cartItems = cartItems;
        this.cartItemClick = cartItemClick;
    }

    public interface CartItemClick {
        void removeItem(CartItem cartItem);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCartBinding binding = ItemCartBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CartItem cartItem = cartItems.get(position);
        holder.binding.tvName.setText(cartItem.getName());
        holder.binding.tvPriceQuantity.setText("Price: " + cartItem.getPrice() +"Quantity: " + cartItem.getQuantity());
        holder.binding.tvDes.setText(cartItem.getDescription());
        String url = cartItem.getImage().get(0);
        String newUrl = url.replace("localhost", "10.0.2.2");
        Glide.with(context)
                .load(newUrl)
                .thumbnail(Glide.with(context).load(R.drawable.camera))
                .into(holder.binding.img);

        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartItemClick.removeItem(cartItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCartBinding binding;

        public ViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}