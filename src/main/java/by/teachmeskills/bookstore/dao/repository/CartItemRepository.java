package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.CartItem;
import by.teachmeskills.bookstore.dao.entity.Order;
import by.teachmeskills.bookstore.dao.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CartItemRepository extends CrudRepository<CartItem, Long> {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    List<CartItem> findByOrder(Order order);

}
