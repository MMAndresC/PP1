package com.svalero.API.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private String name;

    private String phone;

    private String address;

    private int gardenSize; //cubic meters
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate contractEnd;

    private boolean vip;

    private double latitude;

    private double longitude;
}
