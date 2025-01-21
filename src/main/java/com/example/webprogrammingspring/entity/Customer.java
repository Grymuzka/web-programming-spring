package com.example.webprogrammingspring.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Customer extends AuditEntity {

    private String firstName;

    private String lastName;

    private String email;

    private String phone;
}
