package simpleserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class  QueryFactory {
    public QueryFactory() {
    }

    public JsonObject getQuery(String path, String query){
        switch (path) {
            case "user":
                UserQuery newUserQuery = new UserQuery(query);
                return newUserQuery.getResponse();
            case "posts":
                PostQuery newPostQuery = new PostQuery(query);
                return newPostQuery.getResponse();
            default:
                JsonObject response = new JsonObject();
                response.addProperty("status", "ERROR");
                response.addProperty("entries", "NULL");
                response.addProperty("data", "NULL");
                return response;
        }
    }
}
