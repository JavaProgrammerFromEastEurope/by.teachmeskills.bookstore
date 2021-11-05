package by.teachmeskills.bookstore.dao.service;

import by.teachmeskills.bookstore.dao.entity.*;

public interface OrderService {

    Order createOrder(ShoppingCart shoppingCart,
                      ShippingAddress shippingAddress,
                      BillingAddress billingAddress,
                      Payment payment,
                      String shippingMethod,
                      User user);

    Order findOne(Long id);
}
