package ua.nure.martseniuk.diplom.security.repository;

import org.springframework.data.repository.CrudRepository;
import ua.nure.martseniuk.diplom.domain.User;

/**
 * @author Roman_Martseniuk
 */
public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
}
