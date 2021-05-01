package com.andrescassanaz.customeragesapp.adapter.controller;

import com.andrescassanaz.customeragesapp.adapter.controller.model.ClientListResponse;
import com.andrescassanaz.customeragesapp.adapter.controller.model.CreateClientRequest;
import com.andrescassanaz.customeragesapp.adapter.controller.model.KpiClientsResponse;
import com.andrescassanaz.customeragesapp.adapter.controller.model.ResponseEnvelope;
import com.andrescassanaz.customeragesapp.application.port.in.CreateClientCommand;
import com.andrescassanaz.customeragesapp.application.port.in.GetClientsCommand;
import com.andrescassanaz.customeragesapp.application.port.in.GetKpiClientsCommand;
import com.andrescassanaz.customeragesapp.domain.Client;
import com.andrescassanaz.customeragesapp.domain.KpiClient;
import com.andrescassanaz.customeragesapp.mocks.Mocks;
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

import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Client Rest Client Adapter Test")
@WebMvcTest(ClientRestAdapter.class)
@ExtendWith(MockitoExtension.class)
public class ClientRestAdapterTest {

    private static final String URL_CREATE_CLIENT = "/api/v1/creacliente";
    private static final String URL_KPI_CLIENTS = "/api/v1/kpideclientes";
    private static final String URL_LIST_CLIENTS = "/api/v1/listclientes";

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
    @DisplayName("When createClient is called, the CreateClientCommand is called once")
    void createClientCallCommand() throws Exception {
        CreateClientRequest clientRequest = Mocks.getCreateClientRequestMock();
        final String requestContent =
                objectMapper.writeValueAsString(clientRequest);

        mockMvc.perform(post(URL_CREATE_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent));

        verify(createClientCommand, times(1)).createClient(any());
    }

    @Test
    @DisplayName("When createClient is called, the adapter return 201 created status")
    void createClientOk() throws Exception {
        CreateClientRequest clientRequest = Mocks.getCreateClientRequestMock();
        final String requestContent =
                objectMapper.writeValueAsString(clientRequest);

        mockMvc.perform(post(URL_CREATE_CLIENT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestContent))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("When getKpiClients is called, the adapter return the kpi information")
    void getKpiClientsOk() throws Exception {

        final var commandResponse = Mocks.getKpiClientDomainMock();

        final var endpointResponse = ResponseEnvelope.ok(
                KpiClientsResponse.from(commandResponse));

        when(getKpiClientsCommand.getKpiClients()).thenReturn(commandResponse);

        final String expectedJson = objectMapper.writeValueAsString(endpointResponse);

        mockMvc.perform(get(URL_KPI_CLIENTS))
                .andExpect(content().json(expectedJson));
    }

    @Test
    @DisplayName("When getClientsList is called, the adapter return the list of clients")
    void getClientsList() throws Exception {

        final var commandResponse = Mocks.getClientListDomainMock();

        final var endpointResponse = ResponseEnvelope.ok(ClientListResponse.from(commandResponse));

        when(getClientsCommand.getClients()).thenReturn(commandResponse);

        final String expectedJson = objectMapper.writeValueAsString(endpointResponse);

        mockMvc.perform(get(URL_LIST_CLIENTS))
                .andExpect(content().json(expectedJson));
    }
}