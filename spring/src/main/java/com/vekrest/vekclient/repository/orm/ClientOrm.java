package com.vekrest.vekclient.repository.orm;

import com.vekrest.entity.Status;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;

@Document(value = "clients")
public record ClientOrm(
        @Id
        String id,
        String name,
        LocalDate birth,
        AddressOrm address,
        Status status
) {
}
