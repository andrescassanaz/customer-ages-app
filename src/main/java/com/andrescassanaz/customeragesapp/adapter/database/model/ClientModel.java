package com.andrescassanaz.customeragesapp.adapter.database.model;

import com.andrescassanaz.customeragesapp.domain.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientModel {
    String firstName;
    String lastName;
    Integer age;
    LocalDate birthdate;

    public static ClientModel from(Client client){
        return ClientModel.builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .age(client.getAge())
                .birthdate(client.getBirthdate())
                .build();
    }
}

