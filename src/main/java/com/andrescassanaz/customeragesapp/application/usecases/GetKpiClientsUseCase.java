package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.adapter.database.ClientDatabaseAdapter;
import com.andrescassanaz.customeragesapp.application.domain.KpiClient;
import com.andrescassanaz.customeragesapp.application.port.in.GetKpiClientsCommand;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetKpiClientsUseCase implements GetKpiClientsCommand {

    private final ClientDatabaseAdapter clientDatabaseAdapter;

    public GetKpiClientsUseCase(ClientDatabaseAdapter clientDatabaseAdapter) {
        this.clientDatabaseAdapter = clientDatabaseAdapter;
    }

    @Override
    public KpiClient getKpiClients() {
        List<Integer> ages = clientDatabaseAdapter.getAllAges();
        var averageAges = getAverageAges(ages);

        return KpiClient.builder()
                .averageAgeOfClients(getAverageAges(ages))
                .standardDeviationAcrossAllAges(getStandardDeviation(ages, averageAges))
                .build();
    }

    private double getAverageAges(List<Integer> ages) {
        return ages.stream()
                .mapToInt(age -> age)
                .average()
                .orElse(0.0);
    }

    private double getStandardDeviation(List<Integer> ages, Double averageAges) {
        val sum = ages.stream()
                .mapToDouble(age -> Math.pow(age - averageAges, 2))
                .sum();
        return Math.sqrt(sum / ages.size());
    }
}
