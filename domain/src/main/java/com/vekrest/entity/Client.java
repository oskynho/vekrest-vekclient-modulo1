package com.vekrest.entity;

import java.time.LocalDate;

public record Client(
        String id,
        String name,
        LocalDate birth,
        Address address,
        Status status
) {
}
