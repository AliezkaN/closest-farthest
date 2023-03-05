package com.nahorniak.closestfarthest.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.nahorniak.closestfarthest.entity.Response;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ResponseWriter {
    public static void write(Response response, int dataSourceType, int executionType, long elapsedTime) {

        if (Objects.isNull(response)) throw new RuntimeException("Something went wrong during processing");
        ObjectMapper mapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

        try {

            File file = new File("response.json");

            List<ResponseWrapper> responses = new ArrayList<>();

            if (!file.exists()) {
                file.createNewFile();
            }else {
                responses = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, ResponseWrapper.class));
            }

            responses.add(new ResponseWrapper()
                    .setResponse(response)
                    .setDate(new Date())
                    .setExecutionType(executionType == 1 ? ExecutionType.SINGLE_THREAD : ExecutionType.MULTI_THREAD)
                    .setDataSourceType(dataSourceType == 1 ? DataSourceType.JSON : DataSourceType.MEMORY)
                    .setElapsedTime(elapsedTime + " milliseconds")
            );

            ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter().with(dateFormat);
            writer.writeValue(file, responses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    @NoArgsConstructor
    @Accessors(chain = true)
    private static class ResponseWrapper {
        @JsonProperty("response")
        private Response response;
        @JsonProperty("dateTime")
        private Date date;
        @JsonProperty("executionType")
        private ExecutionType executionType;
        @JsonProperty("dataSource")
        private DataSourceType dataSourceType;
        @JsonProperty("elapsedTime")
        private String elapsedTime;
    }

    private enum ExecutionType {
        SINGLE_THREAD,
        MULTI_THREAD
    }

    private enum DataSourceType{
        JSON,
        MEMORY
    }
}
