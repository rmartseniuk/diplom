package ua.nure.martseniuk.diplom.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.nure.martseniuk.diplom.domain.Account;

/**
 * @author Roman_Martseniuk
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Account findByName(String name);
}
