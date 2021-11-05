package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.UserShipping;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserShippingRepository extends CrudRepository<UserShipping, Long> {

}
