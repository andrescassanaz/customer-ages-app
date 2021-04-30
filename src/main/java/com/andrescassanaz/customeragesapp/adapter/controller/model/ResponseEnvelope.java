package com.andrescassanaz.customeragesapp.adapter.controller.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ResponseEnvelope<T> {
    private Integer status;
    private T data;

    public static <T> ResponseEnvelope ok(T data) {
        return ResponseEnvelope.builder()
                .status(HttpStatus.OK.value())
                .data(data)
                .build();
    }

    public static <T> ResponseEnvelope of(Integer status, T data) {
        return ResponseEnvelope.builder()
                .status(status)
                .data(data)
                .build();
    }
}
