package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.application.port.in.CreateClientCommand;
import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateClientUseCase implements CreateClientCommand {

    private final ClientRepository clientRepository;

    public CreateClientUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createClient(Client client) {
        clientRepository.insertClient(client);
    }
}
