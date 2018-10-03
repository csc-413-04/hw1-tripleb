package simpleserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class  QueryFactory {
    public QueryFactory() {
    }

    public JsonObject getQuery(String path, String query){
        if(path.equals("user")) {
            UserQuery newUserQuery = new UserQuery(query);
            return newUserQuery.getResponse();
        }
        else if(path.equals("posts")) {
            PostQuery newPostQuery = new PostQuery(query);
            return newPostQuery.getResponse();
        }
        else {
            JsonObject response = new JsonObject();
            response.addProperty("status", "ERROR");
            response.addProperty("entries", "NULL");
            response.addProperty("data", "NULL");
            return response;
        }
    }
}
