package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.application.domain.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientListResponse {
    List<ClientResponse> clients;

    public static ClientListResponse from(List<Client> clientList) {
        return ClientListResponse.builder()
                .clients(clientList.stream()
                        .map(client -> ClientResponse.from(client))
                        .collect(Collectors.toList()))
                .build();
    }
}
