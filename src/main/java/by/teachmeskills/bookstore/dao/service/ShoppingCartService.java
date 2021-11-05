package by.teachmeskills.bookstore.dao.service;

import by.teachmeskills.bookstore.dao.entity.ShoppingCart;

public interface ShoppingCartService {
    ShoppingCart updateShoppingCart(ShoppingCart shoppingCart);

    void clearShoppingCart(ShoppingCart shoppingCart);
}
