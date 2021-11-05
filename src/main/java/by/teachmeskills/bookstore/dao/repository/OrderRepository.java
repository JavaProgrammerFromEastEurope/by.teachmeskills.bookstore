package by.teachmeskills.bookstore.dao.repository;

import by.teachmeskills.bookstore.dao.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

}
