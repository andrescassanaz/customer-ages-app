package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Kpi Client Response Test")
public class KpiClientsResponseTest {

    @Test
    @DisplayName("When from is called, return a Kpi Client Response")
    void fromOk(){
        var expectedResponse = Mocks.getKpiClientResponseMock();
        var actualResponse = KpiClientsResponse.from(Mocks.getKpiClientDomainMock());
        assertEquals(expectedResponse, actualResponse);
    }
}