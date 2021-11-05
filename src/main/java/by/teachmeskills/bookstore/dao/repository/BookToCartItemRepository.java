package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.BookToCartItem;
import by.teachmeskills.bookstore.dao.entity.CartItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookToCartItemRepository extends CrudRepository<BookToCartItem, Long> {
    void deleteByCartItem(CartItem cartItem);
}
