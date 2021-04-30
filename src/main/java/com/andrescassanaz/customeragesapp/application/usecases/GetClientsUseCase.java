package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.adapter.database.ClientDatabaseAdapter;
import com.andrescassanaz.customeragesapp.application.domain.Client;
import com.andrescassanaz.customeragesapp.application.port.in.GetClientsCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class GetClientsUseCase implements GetClientsCommand {

    private static final Double PERU_LIFE_EXPECTANCY = 76.52;

    private final ClientDatabaseAdapter clientDatabaseAdapter;

    public GetClientsUseCase(ClientDatabaseAdapter clientDatabaseAdapter) {
        this.clientDatabaseAdapter = clientDatabaseAdapter;
    }

    @Override
    public List<Client> getClients() {
        List<Client> clients = clientDatabaseAdapter.getAllClients();
        return clients.stream()
                .map(client -> client.withEstimatedDateOfDeath(
                        calculateDateOfDeath(client.getAge())))
                .collect(Collectors.toList());
    }

    private LocalDate calculateDateOfDeath(Integer age){
        Double remainingTime = PERU_LIFE_EXPECTANCY - age;
        Integer remainingYears = remainingTime.intValue();
        Double remainingDays = (remainingTime - remainingYears) * 365;
        LocalDate estimatedDateOfDeath = LocalDate.now().plusYears(remainingYears.intValue()).plusDays(remainingDays.intValue());
        return estimatedDateOfDeath;
    }
}
