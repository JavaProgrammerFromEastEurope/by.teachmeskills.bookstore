package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {

}
