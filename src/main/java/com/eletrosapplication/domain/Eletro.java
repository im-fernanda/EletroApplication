package com.eletrosapplication.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Entity
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
