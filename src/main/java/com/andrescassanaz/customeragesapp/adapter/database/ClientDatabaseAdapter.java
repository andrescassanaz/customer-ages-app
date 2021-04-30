package com.andrescassanaz.customeragesapp.adapter.database;

import com.andrescassanaz.customeragesapp.adapter.database.model.ClientModel;
import com.andrescassanaz.customeragesapp.domain.Client;
import com.andrescassanaz.customeragesapp.application.port.out.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ClientDatabaseAdapter implements ClientRepository {

    private final AppJdbcTemplate appJdbcTemplate;
    private final String insertClientSql;
    private final String getAllAgesSql;
    private final String getAllClients;
    private static final String INSERT_CLIENT_SQL_PATH = "sql/insertClient.sql";
    private static final String GET_ALL_AGES_SQL_PATH = "sql/getAllAges.sql";
    private static final String GET_ALL_CLIENTS_SQL_PATH = "sql/getAllClients.sql";

    public ClientDatabaseAdapter(AppJdbcTemplate appJdbcTemplate, SqlReader sqlReader) {
        this.appJdbcTemplate = appJdbcTemplate;
        this.insertClientSql = sqlReader.readSql(INSERT_CLIENT_SQL_PATH);
        this.getAllAgesSql= sqlReader.readSql(GET_ALL_AGES_SQL_PATH);
        this.getAllClients = sqlReader.readSql(GET_ALL_CLIENTS_SQL_PATH);
    }

    @Override
    public int insertClient(Client client) {
        final ClientModel model = ClientModel.from(client);
        log.info("Se va a insertar el cliente {}", model);
        final SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(model);
        return appJdbcTemplate.insert(insertClientSql, sqlParameterSource);
    }

    @Override
    public List<Integer> getAllAges(){
        return appJdbcTemplate.queryForList(getAllAgesSql, Integer.class);
    }

    @Override
    public List<Client> getAllClients(){
        List<ClientModel> clientList = appJdbcTemplate.query(getAllClients, ClientModel.class);
        return clientList
                .stream()
                .map(client -> Client.builder()
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .age(client.getAge())
                        .birthdate(client.getBirthdate())
                        .build())
                .collect(Collectors.toList());
    }
}
