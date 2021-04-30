package com.andrescassanaz.customeragesapp.adapter.controller;

import com.andrescassanaz.customeragesapp.adapter.controller.model.CreateClientRequest;
import com.andrescassanaz.customeragesapp.application.port.in.CreateClientCommand;
import com.andrescassanaz.customeragesapp.application.port.in.GetClientsCommand;
import com.andrescassanaz.customeragesapp.application.port.in.GetKpiClientsCommand;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DisplayName("Client Rest Client Adapter Test")
@WebMvcTest(ClientRestAdapter.class)
@ExtendWith(MockitoExtension.class)
public class ClientRestAdapterTest {

    private static final String URL_CREATE_CLIENT = "/api/v1/creacliente";
    private static final Integer CLIENT_CREATED_ID = 1;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateClientCommand createClientCommand;

    @MockBean
    private GetKpiClientsCommand getKpiClientsCommand;

    @MockBean
    private GetClientsCommand getClientsCommand;

    @Test
    @DisplayName("When createClient is called, the CreateClientCommand should return the id of the created resource")
    void createClientOk() throws Exception {
        CreateClientRequest clientRequest = CreateClientRequest.builder()
                .firstName("Andres")
                .lastName("Cassanaz")
                .age(32)
                .birthdate(LocalDate.of(1988,11,29))
                .build();
        final String requestContent =
                objectMapper.writeValueAsString(clientRequest);

        mockMvc.perform(post(URL_CREATE_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent))
                .andReturn();
        verify(createClientCommand, times(1)).createClient(any());
    }
}
