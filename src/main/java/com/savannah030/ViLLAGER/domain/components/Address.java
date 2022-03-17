package com.savannah030.ViLLAGER.domain.components;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

}
