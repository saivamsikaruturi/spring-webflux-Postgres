package com.example.ReactivewithPostgresql.Router;

import com.example.ReactivewithPostgresql.Handler.ReviewHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;

@Configuration
public class MovieRouter {
    @Autowired
    ReviewHandler reviewHandler;

    @Bean
    public RouterFunction<ServerResponse> getMovieRewiews()  {
        return RouterFunctions.route().nest(path("/router"),builder -> {
            builder.POST("/addReview",reviewHandler::addReviews)
                            .GET("/reviews/{movieInfoId}",reviewHandler::getAllReviews)
                    .PUT("/updateReview/{id}",reviewHandler::updateReviews)
                    .DELETE("/{id}",reviewHandler::deleteReview);
                })
                .build();
    }
}
