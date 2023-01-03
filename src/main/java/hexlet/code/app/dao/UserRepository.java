package hexlet.code.app.dao;

import hexlet.code.app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserById(Long id);
    List<User> findAll();
    User findUserByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
    Optional<User> findByFirstName(String username);
}
