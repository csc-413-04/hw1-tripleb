package simpleserver;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostQuery extends Query {
    String[] queryParams;
    String mQuery;

    public PostQuery (String query) {
        mQuery = query;
        queryParams = query.split("&");
        data = new JsonArray();
    }

    public JsonArray getResponse() {
        Database database = Database.getInstance();
        if (queryParams == null) {
            queryParams = mQuery.split("=");
            if (queryParams == null) {
                data = null;
                entries = 0;
                status = "Error";
                return data;
            } else {
                entries = 1;
                status = "OK";
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("postid", database.getPost(queryParams[1]).getPostId());
                jsonObject.addProperty("userid", database.getPost(queryParams[1]).getUserId());
                jsonObject.addProperty("data", database.getPost(queryParams[1]).getPostData());
                data.add(jsonObject);
                return data;
            }
        } else {
            Map<String, String> dataMap = new HashMap<String, String>();
            for (String q : queryParams) {
                String[] qa = q.split("=");
                String id = qa[0];
                String value = qa[1];
                dataMap.put(id, value);
            }
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("postid", database.getPostByLength(dataMap.get("postid"),
                    dataMap.get("maxlength")).getPostId());
            jsonObject.addProperty("postid", database.getPostByLength(dataMap.get("postid"),
                    dataMap.get("maxlength")).getUserId());
            jsonObject.addProperty("postid", database.getPostByLength(dataMap.get("postid"),
                    dataMap.get("maxlength")).getPostData());
            status = "OK";
            entries = jsonObject.size();
            data.add(jsonObject);
            return data;
        }
    }
}
