package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.application.port.in.GetKpiClientsCommand;
import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.domain.KpiClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class GetKpiClientsUseCase implements GetKpiClientsCommand {

    private final ClientRepository clientRepository;

    public GetKpiClientsUseCase(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    private final static Double ZERO = 0.0;

    @Override
    public KpiClient getKpiClients() {
        List<Integer> ages = clientRepository.getAllAges();
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
                .orElse(ZERO);
    }

    private double getStandardDeviation(List<Integer> ages, Double averageAges) {
        if (ages.isEmpty()) return ZERO;
        val sum = ages.stream()
                .mapToDouble(age -> Math.pow(age - averageAges, 2))
                .sum();
        return Math.sqrt(sum / ages.size());
    }
}
