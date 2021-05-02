package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.application.port.in.GetClientsCommand;
import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.domain.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GetClientsUseCase implements GetClientsCommand {

    private static final Double PERU_LIFE_EXPECTANCY = 76.52;

    private final ClientRepository clientRepository;

    public GetClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = clientRepository.getAllClients();
        return clients.stream()
                .map(client -> client.withEstimatedDateOfDeath(
                        calculateDateOfDeath(client.getBirthdate())))
                .collect(Collectors.toList());
    }

    private LocalDate calculateDateOfDeath(LocalDate birthdate) {
        Integer remainingYears = PERU_LIFE_EXPECTANCY.intValue();
        Double remainingDays = (PERU_LIFE_EXPECTANCY - remainingYears) * 365;
        LocalDate estimatedDateOfDeath = birthdate.plusYears(remainingYears).plusDays(remainingDays.intValue());
        return estimatedDateOfDeath;
    }
}
