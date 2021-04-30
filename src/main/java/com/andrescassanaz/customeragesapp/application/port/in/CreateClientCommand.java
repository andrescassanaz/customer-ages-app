package com.andrescassanaz.customeragesapp.application.port.in;

import com.andrescassanaz.customeragesapp.domain.Client;

public interface CreateClientCommand {
    void createClient(Client client);
}
