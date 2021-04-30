package com.andrescassanaz.customeragesapp.application.port.in;

import com.andrescassanaz.customeragesapp.application.domain.Client;

public interface CreateClientCommand {
    void createClient(Client client);
}
