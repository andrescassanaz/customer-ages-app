package com.andrescassanaz.customeragesapp.mocks;

import com.andrescassanaz.customeragesapp.adapter.controller.model.ClientListResponse;
import com.andrescassanaz.customeragesapp.adapter.controller.model.ClientResponse;
import com.andrescassanaz.customeragesapp.adapter.controller.model.CreateClientRequest;
import com.andrescassanaz.customeragesapp.adapter.controller.model.KpiClientsResponse;
import com.andrescassanaz.customeragesapp.adapter.database.model.ClientModel;
import com.andrescassanaz.customeragesapp.domain.Client;
import com.andrescassanaz.customeragesapp.domain.KpiClient;

import java.time.LocalDate;
import java.util.List;

public class Mocks {

    public static CreateClientRequest getCreateClientRequestMock() {
        return CreateClientRequest.builder()
                .firstName("Andres")
                .lastName("Cassanaz")
                .age(32)
                .birthdate(LocalDate.of(1988, 11, 29))
                .build();
    }

    public static Client getClientDomainMock() {
        return Client.builder()
                .firstName("Andres")
                .lastName("Cassanaz")
                .age(32)
                .birthdate(LocalDate.of(1988, 11, 29))
                .build();
    }

    public static List<Integer> getAllAgesMock(){
        return List.of(20,40,60,80);
    }

    public static KpiClient getKpiClientDomainMock(){
        return KpiClient.builder()
                .averageAgeOfClients(50.0)
                .standardDeviationAcrossAllAges(22.360679774997898)
                .build();
    }

    public static KpiClientsResponse getKpiClientResponseMock(){
        return KpiClientsResponse.builder()
                .averageAgeOfClients(50.0)
                .standardDeviationAcrossAllAges(22.360679774997898)
                .build();
    }

    public static List<Client> getClientListDomainMock() {
        return List.of(
                Client.builder()
                        .firstName("Andres")
                        .lastName("Cassanaz")
                        .age(32)
                        .birthdate(LocalDate.of(1988, 11, 29))
                        .build(),
                Client.builder()
                        .firstName("Rocio")
                        .lastName("Diaz")
                        .age(32)
                        .birthdate(LocalDate.of(1989, 2, 14))
                        .build()
        );
    }

    public static List<Client> getClientListWithDeathDateDomainMock() {
        return List.of(
                Client.builder()
                        .firstName("Andres")
                        .lastName("Cassanaz")
                        .age(32)
                        .birthdate(LocalDate.of(1988, 11, 29))
                        .estimatedDateOfDeath(LocalDate.of(2065,6,6))
                        .build(),
                Client.builder()
                        .firstName("Rocio")
                        .lastName("Diaz")
                        .age(32)
                        .birthdate(LocalDate.of(1989, 2, 14))
                        .estimatedDateOfDeath(LocalDate.of(2065, 8, 22))
                        .build()
        );
    }

    public static List<ClientModel> getClientModelListMock(){
        return List.of(
                ClientModel.builder()
                        .firstName("Andres")
                        .lastName("Cassanaz")
                        .age(32)
                        .birthdate(LocalDate.of(1988, 11, 29))
                        .build(),
                ClientModel.builder()
                        .firstName("Rocio")
                        .lastName("Diaz")
                        .age(32)
                        .birthdate(LocalDate.of(1989, 2, 14))
                        .build()
        );
    }

    public static ClientListResponse getClientListResponseMock(){
        return ClientListResponse.builder()
                .clients(List.of(
                        ClientResponse.builder()
                                .firstName("Andres")
                                .lastName("Cassanaz")
                                .age(32)
                                .birthdate(LocalDate.of(1988, 11, 29))
                                .estimatedDateOfDeath(LocalDate.of(2065,6,6))
                                .build(),
                        ClientResponse.builder()
                                .firstName("Rocio")
                                .lastName("Diaz")
                                .age(32)
                                .birthdate(LocalDate.of(1989, 2, 14))
                                .estimatedDateOfDeath(LocalDate.of(2065, 8, 22))
                                .build()
                ))
                .build();
    }

    public static ClientModel getClientModelMock() {
        return ClientModel.builder()
                .firstName("Andres")
                .lastName("Cassanaz")
                .age(32)
                .birthdate(LocalDate.of(1988, 11, 29))
                .build();
    }

    public static ClientResponse getClickResponseMock() {
        return ClientResponse.builder()
                .firstName("Andres")
                .lastName("Cassanaz")
                .age(32)
                .birthdate(LocalDate.of(1988, 11, 29))
                .build();
    }
}
