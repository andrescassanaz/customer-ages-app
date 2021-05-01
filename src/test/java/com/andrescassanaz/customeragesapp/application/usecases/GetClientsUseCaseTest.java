package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.domain.Client;
import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Get Clients Use Case Test")
@ExtendWith(MockitoExtension.class)
public class GetClientsUseCaseTest {

    @Test
    @DisplayName("When getClients is called, the usecase return a list of Client domain")
    void getClientsOk() {
        ClientRepository mockedClientRepository = Mockito.mock(ClientRepository.class);

        var expectedResponse = Mocks.getClientListWithDeathDateDomainMock();

        when(mockedClientRepository.getAllClients()).thenReturn(Mocks.getClientListDomainMock());

        GetClientsUseCase usecase = new GetClientsUseCase(mockedClientRepository);

        List<Client> actualResponse = usecase.getClients();
        assertEquals(expectedResponse, actualResponse);
    }

}