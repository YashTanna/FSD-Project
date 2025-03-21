package com.example.backend.dto;

import jakarta.persistence.Embeddable;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}
