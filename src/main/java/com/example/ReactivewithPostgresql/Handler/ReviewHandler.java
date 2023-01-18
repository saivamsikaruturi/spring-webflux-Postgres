package com.example.ReactivewithPostgresql.Handler;

import com.example.ReactivewithPostgresql.exception.ReviewDataException;
import com.example.ReactivewithPostgresql.model.Customer;
import com.example.ReactivewithPostgresql.model.Review;
import com.example.ReactivewithPostgresql.repository.CustomerRepository;
import com.example.ReactivewithPostgresql.repository.ReviewRepository;
import lombok.extern.log4j.Log4j2;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;




@Component
@Service
@Log4j2
@EnableAutoConfiguration(exclude = {ValidationAutoConfiguration.class})
public class ReviewHandler {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CustomerRepository customerRepository;



    public Mono<ServerResponse> addReviews(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Review.class)
               // .doOnNext(this::validate)
                .flatMap(customer ->
                        reviewRepository.save(customer))
                .flatMap(ServerResponse.status(HttpStatus.CREATED)::bodyValue);
    }

//    private void validate(Review review){
//     //   Errors errors = new BeanPropertyBindingResult(review, "review");
//        var validate =this.validator.validate(review);
//        System.out.println("validate"+validate);
//        if(validate.size()>0) {
//            var error = validate.stream().map(ConstraintViolation::getMessage)
//                    .sorted()
//                    .collect(Collectors.joining(","));
//            throw  new ReviewDataException(error);
//        }
//   }

    public Mono<ServerResponse> getAllReviews(ServerRequest serverRequest){
        var movieInfoId=serverRequest.pathVariable("movieInfoId");
            var reviewById=  reviewRepository.findReviewsByMovieInfoId(Integer.valueOf(movieInfoId));
            return ServerResponse.ok().body(reviewById, Review.class).switchIfEmpty(ServerResponse.badRequest().build());

    }

    public Mono<ServerResponse> updateReviews(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");
        var existingReview = reviewRepository.findById(Integer.valueOf(id));
       return existingReview.flatMap(review -> serverRequest.bodyToMono(Review.class)
               .map(reqReview->{
                   review.setComment(reqReview.getComment());
                   review.setRating(reqReview.getRating());
                   return  review;
               })
               .flatMap(reviewRepository::save)
       ).flatMap(updatedReview->ServerResponse.ok().bodyValue(updatedReview));
    }

    public Mono<ServerResponse> deleteReview(ServerRequest serverRequest){
        var id = serverRequest.pathVariable("id");
        var existingReview = reviewRepository.findById(Integer.valueOf(id));
        return existingReview.flatMap(review -> reviewRepository.deleteById(review.getReviewId())
                .then(ServerResponse.noContent().build()));
    }



}
