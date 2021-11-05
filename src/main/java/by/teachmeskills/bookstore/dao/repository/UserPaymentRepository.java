package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.UserPayment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

}
