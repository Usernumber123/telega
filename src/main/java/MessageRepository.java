
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public  interface MessageRepository extends MongoRepository<Message, String> {
    //  @Autowired
    // private  MongoTemplate mongoTemplate ;
    public List<Message> findByFromAndTo(String From,String To);

}