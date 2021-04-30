package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.adapter.database.exception.DatabaseException;
import com.andrescassanaz.customeragesapp.config.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class SqlReader {

    public String readSql(final String sqlPath) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(sqlPath);
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8.name())) {
            textBuilder.append(FileCopyUtils.copyToString(reader));
            return textBuilder.toString();
        } catch (IOException ex) {
            log.error("Error al leer el archivo sql", ex);
            throw new DatabaseException(ErrorCode.INTERNAL_ERROR, ex.getMessage(), ex);
        }
    }

}

