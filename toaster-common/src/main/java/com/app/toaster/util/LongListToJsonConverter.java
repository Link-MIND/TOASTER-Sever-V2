package com.app.toaster.util;

import java.util.List;

import com.app.toaster.exception.Error;
import com.app.toaster.exception.model.CustomException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LongListToJsonConverter implements AttributeConverter<List<Long>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (Exception e) {
            throw new CustomException(Error.UNPROCESSABLE_ENTITY_CONVERT_EXCEPTION, Error.UNPROCESSABLE_ENTITY_CONVERT_EXCEPTION.getMessage());
        }
    }

    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<Long>>() {});
        } catch (Exception e) {
            throw new CustomException(Error.UNPROCESSABLE_ENTITY_CONVERT_EXCEPTION, Error.UNPROCESSABLE_ENTITY_CONVERT_EXCEPTION.getMessage());
        }
    }
}