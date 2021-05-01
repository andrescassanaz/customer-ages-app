package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Client List Response Test")
public class ClientListResponseTest {

    @Test
    @DisplayName("When from is called, return a Client List Response")
    void fromOk(){
        var expectedResponse = Mocks.getClientListResponseMock();
        var actualResponse = ClientListResponse.from(Mocks.getClientListWithDeathDateDomainMock());
        assertEquals(expectedResponse, actualResponse);
    }
}