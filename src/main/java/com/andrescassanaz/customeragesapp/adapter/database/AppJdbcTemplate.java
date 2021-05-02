package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.adapter.database.exception.DatabaseException;
import com.andrescassanaz.customeragesapp.adapter.database.exception.DuplicateElementException;
import com.andrescassanaz.customeragesapp.adapter.database.exception.InconsistentDataException;
import com.andrescassanaz.customeragesapp.config.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@Component
@Slf4j
public class AppJdbcTemplate {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final JdbcOperations jdbcOperations;

    public AppJdbcTemplate(NamedParameterJdbcTemplate jdbcTemplate, JdbcOperations jdbcOperations) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcOperations = jdbcOperations;
    }

    public int insert(String sql, SqlParameterSource source) {
        return safeExecute(() -> {
            log.info("Se va a insertar el registro de la clase {} para SQL \n{}", source, sql);
            final KeyHolder keyHolder = new GeneratedKeyHolder();
            final int numberOfAffectedRows = jdbcTemplate.update(sql, source, keyHolder, new String[]{"id"});
            final int generatedId = Optional.ofNullable(keyHolder.getKey()).map(Number::intValue).orElse(-1);
            log.info("Se insertó {} registro con id {}", numberOfAffectedRows, generatedId);
            return generatedId;
        });
    }

    public <T> List<T> queryForList(String sql, Class<T> clazz) {
        return safeExecute(() -> {
            log.info("Se van a buscar registros de la clase {} para SQL: \n{}", clazz.getSimpleName(), sql);
            return jdbcOperations.queryForList(sql, clazz);
        });
    }

    public <T> List<T> query(String sql, Class<T> clazz) {
        return safeExecute(() -> {
            log.info("Se van a buscar registros de la clase {} para SQL \n{}",
                    clazz.getSimpleName(), sql);
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(clazz));
        });
    }

    private <T> T safeExecute(Supplier<T> operation) {
        try {
            return operation.get();
        } catch (DuplicateKeyException e) {
            throw new DuplicateElementException(ErrorCode.DUPLICATE_KEY, e);
        } catch (DataIntegrityViolationException e) {
            throw new InconsistentDataException(ErrorCode.INTEGRITY_VIOLATION,
                    "algún campo no cumple con las condiciones necesarias", e);
        } catch (DataAccessException e) {
            throw new DatabaseException(ErrorCode.INTERNAL_ERROR, "los datos no pueden accederse en este momento", e);
        }
    }

}
