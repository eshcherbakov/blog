package org.parma.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Сущность блога.
 */
@Entity
@Table(name = "blog")
@Data
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    // Минимальное количество символов в наименовании блога
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Описание блога.
     * Может принимать значение NULL
     */
    @Column(name = "description", nullable = true)
    private String description;
}
