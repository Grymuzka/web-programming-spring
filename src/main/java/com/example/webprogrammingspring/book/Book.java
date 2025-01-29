package com.example.webprogrammingspring.book;

import com.example.webprogrammingspring.author.Author;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tytuł nie może być pusty")
    @Size(min = 1, max = 255, message = "Tytuł musi mieć od 1 do 255 znaków")
    private String title;

    @Min(value = 1900, message = "Rok publikacji musi być większy niż 1900")
    private int publishYear;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @NotNull(message = "Autor nie może być null")
    @Valid
    private Author author;
}