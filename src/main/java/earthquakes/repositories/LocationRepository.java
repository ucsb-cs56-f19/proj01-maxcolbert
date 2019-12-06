package earthquakes.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import earthquakes.entities.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
   List<Location> findByPlaceId(String placeId);
   List<Location> findByUid(String uid);
}
