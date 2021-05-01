package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.adapter.database.model.ClientModel;
import com.andrescassanaz.customeragesapp.domain.Client;
import com.andrescassanaz.customeragesapp.mocks.Mocks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("Client Database Adapter Test")
@ExtendWith(MockitoExtension.class)
public class ClientDatabaseAdapterTest {

    private static final SqlReader sqlReader = mock(SqlReader.class);
    private static final String SQL = "SELECT 1;";
    private static final SqlParameterSource SOURCE = new BeanPropertySqlParameterSource(Mocks.getClientModelMock());

    @BeforeAll
    static void setSqlReader() {
        when(sqlReader.readSql(anyString())).thenReturn(SQL);
    }

    @Test
    @DisplayName("When insertClient is called, return the created client id")
    void insertClientOk() {
        AppJdbcTemplate appJdbcTemplate = Mockito.mock(AppJdbcTemplate.class);
        ClientDatabaseAdapter adapter = new ClientDatabaseAdapter(appJdbcTemplate, sqlReader);

        var expectedCreatedId = 1;
        when(appJdbcTemplate.insert(any(), any())).thenReturn(1);

        var actualResponse = adapter.insertClient(Mocks.getClientDomainMock());
        assertEquals(expectedCreatedId, actualResponse);
    }

    @Test
    @DisplayName("When getAllAges is called, returns a list of integers")
    void getAllAgesOk() {
        AppJdbcTemplate appJdbcTemplate = Mockito.mock(AppJdbcTemplate.class);
        ClientDatabaseAdapter adapter = new ClientDatabaseAdapter(appJdbcTemplate, sqlReader);

        var expectedResponse = List.of(20, 30);
        when(appJdbcTemplate.queryForList(any(), any())).thenReturn(List.of(20, 30));

        var actualResponse = adapter.getAllAges();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("When getAllClients is called, returns a list of clients")
    void getAllClientsOk() {
        AppJdbcTemplate appJdbcTemplate = Mockito.mock(AppJdbcTemplate.class);
        ClientDatabaseAdapter adapter = new ClientDatabaseAdapter(appJdbcTemplate, sqlReader);

        var expectedResponse = List.of(Mocks.getClientDomainMock());
        when(appJdbcTemplate.query(any(), any())).thenReturn(List.of(Mocks.getClientModelMock()));

        var actualResponse = adapter.getAllClients();
        assertEquals(expectedResponse, actualResponse);
    }
}
