package com.sumit.java.spark.UserManagement;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import spark.Response;
import spark.ResponseTransformer;

import java.io.IOException;
import java.util.HashMap;
 
public class JsonConverter implements ResponseTransformer {
 
    private Gson gson = new Gson();
 
    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}