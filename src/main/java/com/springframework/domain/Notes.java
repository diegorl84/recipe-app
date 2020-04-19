package com.springframework.domain;



import lombok.*;

import java.util.Set;

import javax.persistence.*;

/**
 * @author diego
 * @since 31/12/2019
 */
@Getter
@Setter
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
