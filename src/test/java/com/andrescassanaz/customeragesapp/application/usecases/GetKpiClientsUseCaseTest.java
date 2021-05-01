package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.domain.KpiClient;
import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Get Kpi Clients Use Case Test")
@ExtendWith(MockitoExtension.class)
public class GetKpiClientsUseCaseTest {

    @Test
    @DisplayName("When getKpiClients is called, the usecase return a KpiClient domain")
    void createClientOk() {
        ClientRepository mockedClientRepository = Mockito.mock(ClientRepository.class);

        var expectedResponse = Mocks.getKpiClientDomainMock();

        when(mockedClientRepository.getAllAges()).thenReturn(Mocks.getAllAgesMock());

        GetKpiClientsUseCase usecase = new GetKpiClientsUseCase(mockedClientRepository);

        KpiClient actualResponse = usecase.getKpiClients();
        assertEquals(expectedResponse, actualResponse);
    }

}