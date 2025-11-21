package com.vekrest.vekclient.vekclient.controller.dto.response;

import com.vekrest.vekclient.entity.Address;

public record ClientResponse(
        String id,
        String name,
        String birth,
        Address address,
        String status
) {
}
