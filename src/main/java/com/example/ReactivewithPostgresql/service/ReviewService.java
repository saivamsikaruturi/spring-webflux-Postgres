package com.example.ReactivewithPostgresql.service;

import com.example.ReactivewithPostgresql.model.Review;
import com.example.ReactivewithPostgresql.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    public Mono<Review> addMovies(Review review){
        return reviewRepository.save(review);
    }
}
