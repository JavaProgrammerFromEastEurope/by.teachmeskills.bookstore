package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    long countAllByRoleName(String admin);
}
