package com.example.ReactivewithPostgresql.Controller;


import com.example.ReactivewithPostgresql.model.Customer;
import com.example.ReactivewithPostgresql.model.Review;
import com.example.ReactivewithPostgresql.repository.CustomerRepository;
import com.example.ReactivewithPostgresql.service.ReviewService;
import io.r2dbc.spi.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    Sinks.Many<Review> reviewMany=Sinks.many().replay().all();
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/getAllCustomers/{id}")
    public Mono<ResponseEntity<Customer>> getCustomers(@PathVariable("id") Integer id){
        System.out.println("hitting");
        System.out.println(customerRepository.findById(id).map(e->e).subscribe(e-> System.out.println(e)));
        return customerRepository.findById(id)
                .map(movieInfo1 -> ResponseEntity.ok()
                        .body(movieInfo1)).switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @GetMapping(value = "/getReviews/stream",produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Review> getMovies(@RequestBody Review movie){
        System.out.println("hhhh");
        return reviewMany.asFlux();
    }
    @PostMapping("/addReviews")
    public Mono<Review> addMovies(@RequestBody Review movie){
        return reviewService.addMovies(movie)
                .doOnNext(savedMovie->reviewMany.tryEmitNext(savedMovie));
    }

}
