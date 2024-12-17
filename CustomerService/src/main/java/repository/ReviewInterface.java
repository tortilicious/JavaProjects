package repository;

import model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReviewInterface extends MongoRepository<Review, String> {
}
