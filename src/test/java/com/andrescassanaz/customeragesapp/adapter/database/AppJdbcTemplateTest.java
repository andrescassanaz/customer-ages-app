package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.adapter.database.model.ClientModel;
import com.andrescassanaz.customeragesapp.mocks.MocksFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("App Jdbc Template Test")
@ExtendWith(MockitoExtension.class)
public class AppJdbcTemplateTest {

    private static final String SQL = "SELECT 1;";
    private static final SqlParameterSource SOURCE = new MapSqlParameterSource("A", 1);

    private final NamedParameterJdbcTemplate jdbcTemplate = mock(NamedParameterJdbcTemplate.class);
    private final JdbcOperations jdbcOperations = mock(JdbcOperations.class);


    @Test
    @DisplayName("When insert is called it should return a default id")
    void insertOk() {
        // Given
        final AppJdbcTemplate appJdbcTemplate = new AppJdbcTemplate(jdbcTemplate, jdbcOperations);
        when(jdbcTemplate.update(eq(SQL), eq(SOURCE), any(KeyHolder.class))).thenReturn(0);

        // When
        final int generatedId = appJdbcTemplate.insert(SQL, SOURCE);

        // Then
        assertEquals(-1, generatedId);
    }

    @Test
    @DisplayName("When query is called it should return the list of results")
    void queryOk() {
        // Given
        final AppJdbcTemplate appJdbcTemplate = new AppJdbcTemplate(jdbcTemplate, jdbcOperations);
        final List<ClientModel> expected = MocksFactory.getClientModelListMock();
        when(jdbcTemplate.query(eq(SQL), isA(BeanPropertyRowMapper.class))).thenReturn(expected);

        // When
        final List<ClientModel> actual = appJdbcTemplate.query(SQL, ClientModel.class);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("When query is called it should return the list of results")
    void queryForListOk() {
        // Given
        final AppJdbcTemplate appJdbcTemplate = new AppJdbcTemplate(jdbcTemplate, jdbcOperations);
        final List<Integer> expected = MocksFactory.getAllAgesMock();
        when(jdbcOperations.queryForList(SQL, Integer.class)).thenReturn(expected);

        // When
        final List<Integer> actual = appJdbcTemplate.queryForList(SQL, Integer.class);

        // Then
        assertEquals(expected, actual);
    }
}
