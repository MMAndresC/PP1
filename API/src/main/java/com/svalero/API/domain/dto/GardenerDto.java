package com.svalero.API.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GardenerDto {
    private String name;

    private String phone;

    private String address;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    private boolean selfEmployed;

    private String licensePlate;
}
