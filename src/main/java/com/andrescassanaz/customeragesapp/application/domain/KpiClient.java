package com.andrescassanaz.customeragesapp.application.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class KpiClient {
    private Double averageAgeOfClients;
    private Double standardDeviationAcrossAllAges;
}
