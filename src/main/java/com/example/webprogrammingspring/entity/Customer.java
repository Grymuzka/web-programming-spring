package com.example.webprogrammingspring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer extends AuditEntity {

//    @NotBlank(message = "Imię jest wymagane")
    private String firstName;

//    @NotBlank(message = "Nazwisko jest wymagane")
    private String lastName;

//    @Email(message = "Podaj poprawny adres e-mail")
    private String email;

//    @NotBlank(message = "Numer telefonu jest wymagany")
    private String phone;

//    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Order> orders = new ArrayList<>();
}
