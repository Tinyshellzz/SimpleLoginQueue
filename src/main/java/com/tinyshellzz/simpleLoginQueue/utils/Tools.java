package com.tinyshellzz.simpleLoginQueue.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tools {
    public static String[] get_player(String user_name) throws IOException {
        user_name = user_name.toLowerCase();
        String[] ret = new String[2];

        String url = String.format("https://api.mojang.com/users/profiles/minecraft/%s?", user_name);
        String body = request(url);
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        JsonElement _id = jsonObject.get("id");
        ret[0] = _id == null ? null : _id.getAsString();
        JsonElement _user_name = jsonObject.get("name");
        ret[1] = _user_name == null ? null : _user_name.getAsString();

        return ret;
    }

    public static String request(String url) throws IOException {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        HttpURLConnection conn;

        URL _url = new URL(url);
        conn = (HttpURLConnection) _url.openConnection();

        // Request setup
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        int status = conn.getResponseCode();

        if (status != 200) {
            return null;
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();

        return responseContent.toString();
    }
}
