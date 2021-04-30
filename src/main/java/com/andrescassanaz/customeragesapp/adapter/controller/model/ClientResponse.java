package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.domain.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {
    String firstName;
    String lastName;
    Integer age;
    LocalDate birthdate;
    LocalDate estimatedDateOfDeath;

    public static ClientResponse from(Client client){
        return ClientResponse.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .birthdate(client.getBirthdate())
                .estimatedDateOfDeath(client.getEstimatedDateOfDeath())
                .build();
    }

}
