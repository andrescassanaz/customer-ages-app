package com.andrescassanaz.customeragesapp.adapter.controller;

import com.andrescassanaz.customeragesapp.adapter.controller.model.ClientListResponse;
import com.andrescassanaz.customeragesapp.adapter.controller.model.CreateClientRequest;
import com.andrescassanaz.customeragesapp.adapter.controller.model.KpiClientsResponse;
import com.andrescassanaz.customeragesapp.adapter.controller.model.ResponseEnvelope;
import com.andrescassanaz.customeragesapp.application.port.in.CreateClientCommand;
import com.andrescassanaz.customeragesapp.application.port.in.GetClientsCommand;
import com.andrescassanaz.customeragesapp.application.port.in.GetKpiClientsCommand;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ClientRestClientAdapter {

    private final CreateClientCommand createClientCommand;
    private final GetKpiClientsCommand getKpiClientsCommand;
    private final GetClientsCommand getClientsCommand;


    public ClientRestClientAdapter(CreateClientCommand createClientCommand, GetKpiClientsCommand getKpiClientsCommand, GetClientsCommand getClientsCommand) {
        this.createClientCommand = createClientCommand;
        this.getKpiClientsCommand = getKpiClientsCommand;
        this.getClientsCommand = getClientsCommand;
    }

    @PostMapping("/creacliente")
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody CreateClientRequest createClientRequest) {
        log.info("Http request to /api/v1/creacliente with data" + createClientRequest);
        createClientCommand.createClient(createClientRequest.toDomain());
    }

    @GetMapping("/kpideclientes")
    public ResponseEnvelope<KpiClientsResponse> getKpiClients(){
        log.info("Http request to /api/v1/kpideclientes");
        val kpiClients = getKpiClientsCommand.getKpiClients();
        return ResponseEnvelope.ok(KpiClientsResponse.from(kpiClients));
    }

    @GetMapping("/listclientes")
    public ResponseEnvelope<ClientListResponse> getClientsList(){
        log.info("Http request to /api/v1/listclientes");
        val clients = getClientsCommand.getClients();
        return ResponseEnvelope.ok(ClientListResponse.from(clients));
    }


}
