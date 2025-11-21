package com.vekrest.vekclient.vekclient.controller.dto.request;

import com.vekrest.vekclient.entity.Address;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
public record ClientUpdateRequest(
        @Valid
        String name,

        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate birth,

        @Valid
        Address address
) {
}
