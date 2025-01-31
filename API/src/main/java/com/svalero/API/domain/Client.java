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
@Entity(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String phone;

    @Column
    private String address;

    @Column(name = "garden_size")
    private int gardenSize; //cubic meters

    @Column(name = "contract_end")
    private LocalDate contractEnd;

    @Column
    private boolean vip;

    @Column
    private double latitude;

    @Column
    private double longitude;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Service> services;

}
