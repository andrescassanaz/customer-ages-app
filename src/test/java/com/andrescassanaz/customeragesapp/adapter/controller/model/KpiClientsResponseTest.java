package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Kpi Client Response Test")
public class KpiClientsResponseTest {

    @Test
    @DisplayName("When from is called, return a Kpi Client Response")
    void fromOk() {
        var expectedResponse = MocksFactory.getKpiClientResponseMock();
        var actualResponse = KpiClientsResponse.from(MocksFactory.getKpiClientDomainMock());
        assertEquals(expectedResponse, actualResponse);
    }
}