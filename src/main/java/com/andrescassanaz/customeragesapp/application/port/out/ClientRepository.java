package com.andrescassanaz.customeragesapp.application.port.out;

import com.andrescassanaz.customeragesapp.domain.Client;

import java.util.List;

public interface ClientRepository {
    int insertClient(Client client);
    List<Integer> getAllAges();
    List<Client> getAllClients();

}
