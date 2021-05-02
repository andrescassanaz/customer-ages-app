package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Create Client Request Test")
public class CreateClientRequestTest {

    @Test
    @DisplayName("When to Domain is called, return a Client Domain")
    void toDomain() {
        var expectedResponse = MocksFactory.getClientDomainMock();
        var actualResponse = MocksFactory.getCreateClientRequestMock().toDomain();
        assertEquals(expectedResponse, actualResponse);
    }
}