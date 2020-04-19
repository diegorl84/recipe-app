package com.springframework.domain;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

/**
 * @author diego
 * @since 06/01/2020
 */
@Getter
@Setter
@Entity
public class UnitOfMeasure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

}
