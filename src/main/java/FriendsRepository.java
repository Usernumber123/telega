import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends MongoRepository<Friends, String> {
    public List<Friends> findByUsername(String username);

}
