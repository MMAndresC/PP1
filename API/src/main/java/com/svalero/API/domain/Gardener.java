package com.svalero.API.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "gardeners")
public class Gardener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "license_plate")
    private String licensePlate;

    @Column(name = "self-employed")
    private boolean selfEmployed;

    @OneToMany(mappedBy = "gardener", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services;
}
