package com.andrescassanaz.customeragesapp.application.port.out;

import com.andrescassanaz.customeragesapp.application.domain.Client;

public interface ClientRepository {
    int insertClient(Client client);
}
