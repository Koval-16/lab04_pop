package ite.kubak.logic;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.http.HttpResponse;

public class Parser {

    private ObjectMapper mapper = new ObjectMapper();

    public Parser(){
    }

    public <T> T parse(HttpResponse<String> response, Class<T> type){
        try{
            T t = mapper.readValue(response.body(),type);
            return t;
        } catch(Exception e){
            return null;
        }
    }

    public ObjectMapper getMapper() {
        return mapper;
    }
}
