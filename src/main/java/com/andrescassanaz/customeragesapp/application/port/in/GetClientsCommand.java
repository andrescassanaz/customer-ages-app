package com.andrescassanaz.customeragesapp.application.port.in;

import com.andrescassanaz.customeragesapp.application.domain.Client;

import java.util.List;

public interface GetClientsCommand {
    List<Client> getClients();
}
