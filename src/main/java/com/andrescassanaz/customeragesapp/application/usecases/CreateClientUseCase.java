package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.adapter.database.ClientDatabaseAdapter;
import com.andrescassanaz.customeragesapp.application.domain.Client;
import com.andrescassanaz.customeragesapp.application.port.in.CreateClientCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateClientUseCase implements CreateClientCommand {

    private final ClientDatabaseAdapter clientDatabaseAdapter;

    public CreateClientUseCase(ClientDatabaseAdapter clientDatabaseAdapter) {
        this.clientDatabaseAdapter = clientDatabaseAdapter;
    }

    @Override
    public void createClient(Client client) {
        log.info("The user will be created: {}", client);
        clientDatabaseAdapter.insertClient(client);
    }
}
