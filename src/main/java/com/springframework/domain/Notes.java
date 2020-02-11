package com.springframework.domain;



import lombok.*;

import javax.persistence.*;

/**
 * @author diego
 * @since 31/12/2019
 */
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;

}
