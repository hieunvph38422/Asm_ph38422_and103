package com.example.asm_ph38422_and103.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.asm_ph38422_and103.R;
import com.example.asm_ph38422_and103.databinding.ItemFruitBinding;
import com.example.asm_ph38422_and103.model.CartItem;
import com.example.asm_ph38422_and103.model.Fruit;
import com.example.asm_ph38422_and103.view.CartManager;

import java.util.ArrayList;

public class FruitAdapter  extends RecyclerView.Adapter<FruitAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Fruit> list;
    private FruitClick fruitClick;

    public FruitAdapter(Context context, ArrayList<Fruit> list, FruitClick fruitClick) {
        this.context = context;
        this.list = list;
        this.fruitClick = fruitClick;
    }

    public interface FruitClick {
        void delete(Fruit fruit);
        void edit(Fruit fruit);
        void showDetail(Fruit fruit);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFruitBinding binding = ItemFruitBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = list.get(position);
        holder.binding.tvName.setText(fruit.getName());
        holder.binding.tvPriceQuantity.setText("price :" +fruit.getPrice()+" - quantity:" +fruit.getQuantity());
        holder.binding.tvDes.setText(fruit.getDescription());
        String url  = fruit.getImage().get(0);
        String newUrl = url.replace("localhost", "10.0.2.2");
        Glide.with(context)
                .load(newUrl)
                .thumbnail(Glide.with(context).load(R.drawable.camera))
                .into(holder.binding.img);

        holder.binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitClick.edit(fruit);
            }
        });
        holder.binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitClick.delete(fruit);
            }
        });
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fruitClick.showDetail(fruit);
            }
        });
        holder.binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Lấy vị trí của mục trong danh sách hiển thị (nếu bạn sử dụng RecyclerView)
                int position = holder.getAdapterPosition();

                // Kiểm tra xem vị trí có hợp lệ không
                if (position != RecyclerView.NO_POSITION) {
                    // Lấy Fruit từ danh sách hoặc mảng Fruit tương ứng
                    Fruit fruit = list.get(position); // Giả sử fruits là danh sách các mặt hàng

                    // Thêm Fruit vào giỏ hàng bằng cách gọi phương thức addToCart của CartManager
                    CartManager.getInstance().addToCart(fruit);

                    // Thông báo cho người dùng rằng sản phẩm đã được thêm vào giỏ hàng
                    Toast.makeText(context, "Đã thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFruitBinding binding;
        public ViewHolder(ItemFruitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
