package by.teachmeskills.bookstore.dao.repository;


import by.teachmeskills.bookstore.dao.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
    Role findAllByNameAndId(String name, Long id);

    Role findByName(String name);
}
