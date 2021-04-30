package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.application.domain.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateClientRequest {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd";

    @NotBlank
    @ApiModelProperty(value = "Nombre", required = true, example = "Andres")
    String firstName;

    @NotBlank
    @ApiModelProperty(value = "Apellido", required = true, example = "Cassanaz")
    String lastName;

    @Positive(message = "El campo 'debe tener un valor positivo")
    @ApiModelProperty(value = "Edad", required = true, example = "32")
    Integer age;

    @JsonFormat(pattern = DATE_TIME_PATTERN)
    @ApiModelProperty(value = "Fecha de nacimiento", required = true, example = "1988-11-29")
    LocalDate birthdate;

    public Client toDomain() {
        return Client.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .age(this.age)
                .birthdate(this.birthdate)
                .build();
    }
}