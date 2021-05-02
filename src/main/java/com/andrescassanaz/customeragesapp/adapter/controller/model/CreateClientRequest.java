package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.andrescassanaz.customeragesapp.adapter.controller.exception.ControllerValidationException;
import com.andrescassanaz.customeragesapp.adapter.controller.validators.BirthdateConstraint;
import com.andrescassanaz.customeragesapp.adapter.controller.validators.BirthdateValidator;
import com.andrescassanaz.customeragesapp.config.ErrorCode;
import com.andrescassanaz.customeragesapp.domain.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Value;
import org.apache.tomcat.jni.Local;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    @ApiModelProperty(value = "Fecha de nacimiento", required = true, example = "1988-11-29")
    @BirthdateConstraint
    String birthdate;

    public Client toDomain() {
        return Client.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .age(this.age)
                .birthdate(LocalDate.parse(this.birthdate))
                .build();
    }


}