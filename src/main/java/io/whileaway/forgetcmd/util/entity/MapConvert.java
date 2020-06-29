package io.whileaway.forgetcmd.util.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.whileaway.forgetcmd.util.enums.CommonErrorEnum;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapConvert<A,B> implements AttributeConverter<Map<A,B>, String> {
    @Override
    public String convertToDatabaseColumn(Map<A,B> map) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            CommonErrorEnum.SERVER_ERROR.throwThis();
        }
        return "{}";
    }

    @Override
    public Map<A,B> convertToEntityAttribute(String s) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(s,  new TypeReference<Map<A, B>>(){});
        } catch (IOException e) {
            CommonErrorEnum.SERVER_ERROR.throwThis();
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
