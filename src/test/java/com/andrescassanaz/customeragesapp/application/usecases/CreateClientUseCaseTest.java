package com.andrescassanaz.customeragesapp.application.usecases;

import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Create Client Use Case Test")
@ExtendWith(MockitoExtension.class)
public class CreateClientUseCaseTest {


    @Test
    @DisplayName("When createClient is called, the insertClient is called once")
    void createClientOk() {
        ClientRepository mockedClientRepository = Mockito.mock(ClientRepository.class);

        CreateClientUseCase usecase = new CreateClientUseCase(mockedClientRepository);
        usecase.createClient(Mocks.getClientDomainMock());
        verify(mockedClientRepository, times(1)).insertClient(any());
    }

}
