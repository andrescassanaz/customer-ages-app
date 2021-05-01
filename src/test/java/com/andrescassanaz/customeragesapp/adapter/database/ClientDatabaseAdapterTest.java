package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import com.andrescassanaz.customeragesapp.application.usecases.CreateClientUseCase;
import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("Client Database Adapter Test")
@ExtendWith(MockitoExtension.class)
public class ClientDatabaseAdapterTest {

    private static final String SQL = "SELECT 1;";
    private static final SqlParameterSource SOURCE = new MapSqlParameterSource("A", 1);

    @Test
    @DisplayName("When insertClient is called, return the created client id")
    void insertClientOk() {
        AppJdbcTemplate appJdbcTemplate = Mockito.mock(AppJdbcTemplate.class);
        SqlReader sqlReader = Mockito.mock(SqlReader.class);
        ClientDatabaseAdapter adapter = new ClientDatabaseAdapter(appJdbcTemplate, sqlReader);

        var expectedCreatedId = 1;
        when(appJdbcTemplate.insert(SQL, SOURCE)).thenReturn(1);
        //when(sqlReader.readSql(anyString())).thenReturn(SQL);

        var actualResponse = adapter.insertClient(Mocks.getClientDomainMock());
        assertEquals(expectedCreatedId, actualResponse);
    }
}
