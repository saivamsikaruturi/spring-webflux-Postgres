//package com.example.ReactivewithPostgresql.unitTests;
//
//import com.example.ReactivewithPostgresql.Handler.ReviewHandler;
//import com.example.ReactivewithPostgresql.Router.MovieRouter;
//import com.example.ReactivewithPostgresql.exceptionHandler.GlobalExceptionHandler;
//import com.example.ReactivewithPostgresql.model.Review;
//import com.example.ReactivewithPostgresql.repository.ReviewRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//@WebFluxTest
//@ContextConfiguration(classes = {MovieRouter.class, ReviewHandler.class, GlobalExceptionHandler.class})
//@AutoConfigureWebTestClient
//@RunWith(SpringRunner.class)
//public class unitTests {
//
//    @MockBean
//    private ReviewRepository reviewReactiveRepository;
//
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//
//    @Test
// public    void addReview() {
//        //given
//       var review = new Review(null, 1, "Awesome Movie", 9);
//
//        Mockito.when(reviewReactiveRepository.save(isA(Review.class))).thenReturn(Mono.just(new Review(1, 1, "Awesome Movie", 9)));
//
//        //when
//        webTestClient
//                .post()
//                .uri("/router/addReview")
//                .bodyValue(review)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Review.class)
//                .consumeWith(reviewResponse ->{
//                    var savedReview = reviewResponse.getResponseBody();
//                    assert savedReview != null;
//                    assertNotNull(savedReview.getReviewId());
//                    assertEquals(1, savedReview.getReviewId());
//
//                });
//
//    }
//
////    @Test
////   public void addReview_Validations() {
////        //given
////        var review = new Review(null, null, "Awesome Movie", -9);
////        //when
////        webTestClient
////                .post()
////                .uri("/router/addReview")
////                .bodyValue(review)
////                .exchange()
////                .expectStatus().isBadRequest()
////                .expectBody(String.class)
////                .isEqualTo("rating.movieInfoId : must not be null");
////
////    }
//
//
//}
