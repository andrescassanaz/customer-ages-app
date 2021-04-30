package com.andrescassanaz.customeragesapp.application.port.in;

import com.andrescassanaz.customeragesapp.application.domain.KpiClient;

public interface GetKpiClientsCommand {
    KpiClient getKpiClients();
}
