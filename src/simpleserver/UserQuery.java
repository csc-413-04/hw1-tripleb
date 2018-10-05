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

    public JsonObject getResponse() {
        Database database = Database.getInstance();
        if (queryParams != null) {
            entries = 1;
            status = "OK";
            try {

                if (!"userid".matches(queryParams[0])) {
                    //System.out.println(queryParams[0] + "LOOKY" + queryParams[1]);
                    JsonObject responseObject = new JsonObject();
                    responseObject.addProperty("status", "ERROR");
                    responseObject.addProperty("entries", "NULL");
                    return responseObject;
                } else {
                    JsonObject responseObject = new JsonObject();
                    responseObject.addProperty("status", status);
                    responseObject.addProperty("entries", entries);
                    JsonObject dataObject = new JsonObject();
                    dataObject.addProperty("userid", database.getUser(queryParams[1]).getUserId());
                    dataObject.addProperty("username", database.getUser(queryParams[1]).getUserName());
                    data.add(dataObject);
                    responseObject.add("data", data);
                    return responseObject;
                }
            } catch (Exception e) {
                JsonObject responseObject = new JsonObject();
                responseObject.addProperty("status", "ERROR");
                responseObject.addProperty("entries", "NULL");
                return responseObject;
            }

        } else {
            entries = 0;
            status = "OK";
            ArrayList<User> users = database.getAllUsers();
            for (User x : users) {
                JsonObject dataObject = new JsonObject();
                dataObject.addProperty("userid", x.getUserId());
                dataObject.addProperty("username", x.getUserName());
                data.add(dataObject);
                entries += 1;
            }
            JsonObject response = new JsonObject();
            response.addProperty("status", status);
            response.addProperty("entries", entries);
            response.add("data", data);
            return response;

        }
    }

}
