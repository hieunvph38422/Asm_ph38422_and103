package com.example.asm_ph38422_and103.view;

import com.example.asm_ph38422_and103.model.CartItem;
import com.example.asm_ph38422_and103.model.Fruit;

import java.util.ArrayList;

public class CartManager {
    private static CartManager instance;
    private ArrayList<CartItem> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void addToCart(Fruit fruit) {
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean found = false;
        for (CartItem item : cartItems) {
            if (item.getId().equals(fruit.get_id())) {
                // Nếu sản phẩm đã tồn tại, tăng số lượng
                item.increaseQuantity();
                found = true;
                break;
            }
        }
        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm mới vào giỏ hàng với số lượng là 1
        if (!found) {
            cartItems.add(new CartItem(fruit.get_id(), fruit.getName(), "1", fruit.getPrice(), fruit.getDescription(), fruit.getImage()));
        }
    }

    public void removeFromCart(CartItem cartItem) {
        // Tìm và xóa sản phẩm khỏi giỏ hàng
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            if (item.getId().equals(cartItem.getId())) {
                // Nếu số lượng của mục trong giỏ hàng là 1, xóa mục khỏi giỏ hàng
                if (item.getQuantity().equals("1")) {
                    cartItems.remove(i);
                } else {
                    // Ngược lại, giảm số lượng sản phẩm đi 1
                    item.decreaseQuantity();
                }
                break;
            }
        }
    }
}
