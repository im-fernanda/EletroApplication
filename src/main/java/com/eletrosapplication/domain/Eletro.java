package com.eletrosapplication.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;




@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "eletro_tbl")
@Data
public class Eletro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Size(min = 2, message = "Houve um erro no cadastro do campo título.")
    @NotBlank (message = "Nome é obrigatório")
    private String nome;

    @NotNull(message = "Preço é obrigatório")
    private Double preco;

    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @NotBlank(message = "Categoria é obrigatória")
    private String categoria;

    private String descricao;
    private String modelo;
    private String imageUrl;

    private LocalDate isDeleted;
}
