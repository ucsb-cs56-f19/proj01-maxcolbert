package hello.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import hello.entities.AppUser;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
   List<AppUser> findByUid(String uid);
}
