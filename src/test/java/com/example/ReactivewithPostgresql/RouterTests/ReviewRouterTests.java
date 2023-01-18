//package com.example.ReactivewithPostgresql.RouterTests;
//
//
//import com.example.ReactivewithPostgresql.model.Review;
//import com.example.ReactivewithPostgresql.repository.ReviewRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.isA;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@AutoConfigureWebTestClient
//public class ReviewRouterTests {
//
//    @Autowired
//    WebTestClient webTestClient;
//
//    @Autowired
//    ReviewRepository reviewReactiveRepository;
//
//    static String REVIEWS_URL = "/router/addReview";
//
//    @BeforeEach
//    void setUp() {
//
//        var reviewsList = List.of(
//                new Review(null, 1, "Awesome Movie", 9),
//                new Review(null, 1, "Awesome Movie1", 9),
//                new Review(null, 2, "Excellent Movie", 8));
//        reviewReactiveRepository.saveAll(reviewsList)
//                .blockLast();
//    }
//
//    @AfterEach
//    void tearDown() {
//        reviewReactiveRepository.deleteAll()
//                .block();
//    }
//
//
//
//    @Test
//    void addReview() {
//        //given
//        var review = new Review(null, 1, "Awesome Movie", 9);
//        //when
//
//        webTestClient
//                .post()
//                .uri(REVIEWS_URL)
//                .bodyValue(review)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(Review.class)
//                .consumeWith(reviewResponse -> {
//                    var savedReview = reviewResponse.getResponseBody();
//                    assert savedReview != null;
//                    assertNotNull(savedReview.getReviewId());
//                });
//
//    }
//
//
//
//}
