package com.alex.bookcity.myssm.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGson {
  public static Gson getGson(){
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

    Gson gson = gsonBuilder.setPrettyPrinting().create();

    return gson;
  }
}
