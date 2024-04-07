package com.example.asm_ph38422_and103.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_ph38422_and103.R;
import com.example.asm_ph38422_and103.adapter.CartAdapter;
import com.example.asm_ph38422_and103.databinding.ActivityCartBinding;
import com.example.asm_ph38422_and103.model.CartItem;
import com.example.asm_ph38422_and103.model.Fruit;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements CartAdapter.CartItemClick{

    ActivityCartBinding binding;
    private ArrayList<CartItem> cartItems;
    private CartAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        ArrayList<CartItem> cartItems = CartManager.getInstance().getCartItems();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rcvCart.setLayoutManager(layoutManager);
        cartAdapter = new CartAdapter(this, cartItems, this);
        binding.rcvCart.setAdapter(cartAdapter);
    }

    @Override
    public void removeItem(CartItem cartItem) {
        CartManager.getInstance().removeFromCart(cartItem);

        // Cập nhật giao diện người dùng
        cartAdapter.notifyDataSetChanged();

        // Hiển thị thông báo khi xóa thành công
        Toast.makeText(this, "Removed from cart: " + cartItem.getName(), Toast.LENGTH_SHORT).show();

        // Kiểm tra xem giỏ hàng còn trống không
        if (CartManager.getInstance().getCartItems().isEmpty()) {
            binding.rcvCart.setVisibility(View.GONE);
        }
    }
}