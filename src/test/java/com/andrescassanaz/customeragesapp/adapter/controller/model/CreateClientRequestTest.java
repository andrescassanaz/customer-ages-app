package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Create Client Request Test")
public class CreateClientRequestTest {

    @Test
    @DisplayName("When to Domain is called, return a Client Domain")
    void toDomain() {
        var expectedResponse = Mocks.getClientDomainMock();
        var actualResponse = Mocks.getCreateClientRequestMock().toDomain();
        assertEquals(expectedResponse, actualResponse);
    }
}