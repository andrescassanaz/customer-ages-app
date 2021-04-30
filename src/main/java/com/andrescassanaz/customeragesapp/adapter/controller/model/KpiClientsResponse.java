package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.domain.KpiClient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class KpiClientsResponse {
    private Double averageAgeOfClients;
    private Double standardDeviationAcrossAllAges;

    public static KpiClientsResponse from(KpiClient kpiClient){
        return KpiClientsResponse.builder()
                .averageAgeOfClients(kpiClient.getAverageAgeOfClients())
                .standardDeviationAcrossAllAges(kpiClient.getStandardDeviationAcrossAllAges())
                .build();
    }
}
