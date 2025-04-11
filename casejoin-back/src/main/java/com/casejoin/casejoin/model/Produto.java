package com.casejoin.casejoin.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "PRECO", nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "ID_CATEGORIA", nullable = false)
    private Categoria categoria;
}
