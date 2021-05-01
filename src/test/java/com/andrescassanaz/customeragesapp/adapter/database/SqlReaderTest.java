package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.adapter.database.exception.DatabaseException;
import com.andrescassanaz.customeragesapp.config.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SqlReader Test")
@ExtendWith(MockitoExtension.class)
public class SqlReaderTest {

    @Test
    @DisplayName("When readSql is called it should read the contents of the file")
    void readSqlOk() {
        // Given
        final SqlReader sqlReader = new SqlReader();
        final String filename = "example.xml";

        // When
        final String contents = sqlReader.readSql(filename);

        // Then
        assertTrue(contents.contains("xml"));
    }

    @Test
    @DisplayName("When readSql is called it should read the contents of the file")
    void readSqlError() {
        // Given
        final SqlReader sqlReader = new SqlReader();
        final String filename = "file-not-found.sql";

        // When
        final DatabaseException e = assertThrows(DatabaseException.class, () -> sqlReader.readSql(filename));

        // Then
        assertEquals(ErrorCode.INTERNAL_ERROR, e.getCode());
    }

}
