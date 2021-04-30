package com.andrescassanaz.customeragesapp.application.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDate;

@Value
@Builder
public class Client {
    String firstName;
    String lastName;
    Integer age;
    LocalDate birthdate;
    @With
    LocalDate estimatedDateOfDeath;
}
