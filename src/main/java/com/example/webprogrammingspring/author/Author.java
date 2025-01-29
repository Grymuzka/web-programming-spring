package com.example.webprogrammingspring.author;

import com.example.webprogrammingspring.book.Book;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Imię i nazwisko nie może być puste")
    @Size(min = 2, max = 100, message = "Imię i nazwisko musi mieć od 2 do 100 znaków")
    private String name;

    @DateTimeFormat
    @Past(message = "Data urodzenia musi być w przeszłości")
    private LocalDate birthDate;

    @Positive(message = "Wzrost nie może być ujemny")
    private float height;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;
}
