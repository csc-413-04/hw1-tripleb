package simpleserver;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class UserQuery extends Query {
    String[] queryParams;

    public UserQuery (String query) {
        if (query != null) {
            queryParams = query.split("=");
        }
        data = new JsonArray();
    }

    public JsonArray getResponse() {
        Database database = Database.getInstance();
        if (queryParams != null) {
            entries = 1;
            status = "OK";
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("userid", database.getUser(queryParams[1]).getUserId());
            jsonObject.addProperty("username", database.getUser(queryParams[1]).getUserName());
            data.add(jsonObject);
            return data;
        } else {
            ArrayList<User> users = database.getAllUsers();
            for (User x : users) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("userid", x.getUserId());
                jsonObject.addProperty("username", x.getUserName());
                data.add(jsonObject);
            }
            entries = users.size();
            status = "OK";
            return data;

        }
    }

}
