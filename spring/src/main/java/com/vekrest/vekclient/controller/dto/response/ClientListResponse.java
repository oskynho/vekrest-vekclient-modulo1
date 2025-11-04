package com.vekrest.vekclient.controller.dto.response;

import java.util.List;

public record ClientListResponse(
        List<ClientResponse> clients
) {
}
