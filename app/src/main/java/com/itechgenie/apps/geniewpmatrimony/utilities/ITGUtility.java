package com.itechgenie.apps.geniewpmatrimony.utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created by Prakash-hp on 20-05-2017.
 */

public class ITGUtility {

    private static ObjectMapper objectMapper = new ObjectMapper( ) ;

    public static String objectToJson(Object inputObj) throws IOException {
        return objectMapper.writeValueAsString(inputObj) ;
    }

    public static Object jsonToObject(String jsonString, Class className) throws IOException {
        return objectMapper.readValue(jsonString, className) ;
    }

}
