package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Client Response Test")
public class ClientResponseTest {

    @Test
    @DisplayName("When from is called, return a ClientResponse")
    void fromOk() {
        var expectedResponse = MocksFactory.getClickResponseMock();
        var actualResponse = ClientResponse.from(MocksFactory.getClientDomainMock());
        assertEquals(expectedResponse, actualResponse);
    }
}
