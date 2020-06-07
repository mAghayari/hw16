package services;

import dao.CartDao;
import dto.cart.Cart;
import dto.cart.CartItem;

import java.util.List;

public class CartServices {

    public Cart cancelOrder(Cart cart) {
        ProductServices productServices = new ProductServices();
        List<CartItem> orderedItems = cart.getCartItems();

        productServices.getAllSoledItemsBack(orderedItems);
        return cart;
    }

    public double getTotalCost(Cart cart) {
        cart.setTotalCost(cart.getCartItems());
        return cart.getTotalCost();
    }

    public void addingOrder(Cart cart) {
        CartDao cartDao = new CartDao();
        cartDao.addACart(cart);
    }
}