package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Client Response Test")
public class ClientResponseTest {

    @Test
    @DisplayName("When from is called, return a ClientResponse")
    void fromOk(){
        var expectedResponse = Mocks.getClickResponseMock();
        var actualResponse = ClientResponse.from(Mocks.getClientDomainMock());
        assertEquals(expectedResponse, actualResponse);
    }
}
