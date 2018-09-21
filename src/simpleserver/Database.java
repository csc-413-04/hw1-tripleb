package simpleserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;

public class Database {
    InputStream inputStream = new FileInputStream("data.json");
    JsonParser jsonParser = new JsonParser();
    JsonObject jsonRootObject = (JsonObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
    JsonArray jsonArray = jsonRootObject.getAsJsonArray("users");

    public Database() throws FileNotFoundException, UnsupportedEncodingException {
    }
}
