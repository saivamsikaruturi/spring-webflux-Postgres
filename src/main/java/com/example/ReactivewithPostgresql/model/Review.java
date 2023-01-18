package com.example.ReactivewithPostgresql.model;



import lombok.*;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="review")
public class Review {

    @Id
    @Column(value = "reviewid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer reviewId;

    @Column(value="movieinfoid")
    @NotNull(message="rating.movieInfoId: must not be null")
    private Integer movieInfoId;
    private String comment;

    private Integer rating;
}
