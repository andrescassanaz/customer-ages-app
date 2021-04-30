package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.application.domain.Client;
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
public class CreateClientRequest {
    String firstName;
    String lastName;
    Integer age;
    LocalDate birthdate;

    public Client toDomain() {
        return Client.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .age(this.age)
                .birthdate(this.birthdate)
                .build();
    }
}