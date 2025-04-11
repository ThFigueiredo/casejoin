package com.casejoin.casejoin.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "CATEGORIA")
public class Categoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Produto> produtos;
}
