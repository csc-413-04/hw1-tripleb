package simpleserver;

<<<<<<< HEAD
public class QueryFactory {

    public Query QueryFactory(String request, String args) {
      switch(request) {
        case "/users":
          return new UserQuery(args);
        break;
        case "/posts":
          return new PostQuery(args);
        break;
      }
      return null;
    }
  }

=======
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class QueryFactory {
    public QueryFactory() {
    }

    public JsonObject getQuery(String path, String query){
        if(path.equals("user")) {
            UserQuery newUserQuery = new UserQuery(query);
            JsonObject response = new JsonObject();
            response.addProperty("status", newUserQuery.status);
            response.addProperty("entries", newUserQuery.entries);
            response.add("data", newUserQuery.getResponse());
            return response;
        }
        else if(path.equals("posts")) {
            PostQuery newPostQuery = new PostQuery(query);
            JsonObject response = new JsonObject();
            response.addProperty("status", newPostQuery.status);
            response.addProperty("entries", newPostQuery.entries);
            response.add("data", newPostQuery.getResponse());
            return response;
        }
        return null;
    }
}
>>>>>>> master
