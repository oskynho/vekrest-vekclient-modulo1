package com.vekrest.vekclient.controller.dto.response;

import com.vekrest.entity.Address;

public record ClientResponse(
        String id,
        String name,
        String birth,
        Address address,
        String status
) {
}
