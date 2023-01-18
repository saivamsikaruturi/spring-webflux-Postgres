package com.example.ReactivewithPostgresql.repository;

import com.example.ReactivewithPostgresql.model.Review;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Component
@Repository
public interface ReviewRepository extends ReactiveCrudRepository<Review, Integer> {

    Flux<Review> findReviewsByMovieInfoId(Integer movieInfoId);
}
