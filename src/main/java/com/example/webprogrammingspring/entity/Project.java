package com.example.webprogrammingspring.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nazwa projektu jest wymagana")
    private String name;

    @NotNull(message = "Data rozpoczęcia jest wymagana")
    private LocalDate startDate;

    private String description;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public enum ProjectStatus {
        PLANOWANY, W_TRAKCIE, ZAKOŃCZONY
    }

}
