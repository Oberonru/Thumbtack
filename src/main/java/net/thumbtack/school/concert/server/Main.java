package net.thumbtack.school.concert.server;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import net.thumbtack.school.concert.User;

import java.io.File;
import java.io.FileReader;
import java.io.StringReader;

public class Main {
    public static void main(String[] args) throws Exception {

        Server server = new Server();
        String jsonString = "{\"login\" : \"manunya\", \"password\" : \"123\" }";

        JsonReader jsonReader = new JsonReader(new StringReader(jsonString));

        while (jsonReader.hasNext()) {
            String array = jsonReader.toString();
            System.out.println(array);
        }

    }
}
