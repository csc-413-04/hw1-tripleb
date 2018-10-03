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

    public JsonObject getResponse() {
        Database database = Database.getInstance();
        if (queryParams == null) {
            queryParams = mQuery.split("=");
            if (queryParams == null) {
                data = null;
                entries = 0;
                status = "Error";
                JsonObject responseObject = new JsonObject();
                responseObject.addProperty("status", status);
                responseObject.addProperty("entries", entries);
                responseObject.add("data", data);
                return responseObject;
            } else {
                entries = 1;
                status = "OK";
                JsonObject responseObject = new JsonObject();
                responseObject.addProperty("status", status);
                responseObject.addProperty("entries", entries);

                JsonObject dataObject = new JsonObject();
                dataObject.addProperty("postid", database.getPost(queryParams[1]).getPostId());
                dataObject.addProperty("userid", database.getPost(queryParams[1]).getUserId());
                dataObject.addProperty("data", database.getPost(queryParams[1]).getPostData());
                data.add(dataObject);
                responseObject.add("data", data);
                return responseObject;
            }
        } else {
            Map<String, String> dataMap = new HashMap<String, String>();
            for (String q : queryParams) {
                String[] qa = q.split("=");
                String id = qa[0];
                String value = qa[1];
                dataMap.put(id, value);
            }
            status = "OK";
            entries = 1;
            JsonObject responseObject = new JsonObject();
            responseObject.addProperty("status", status);
            responseObject.addProperty("entries", entries);

            JsonObject dataObject = new JsonObject();
            dataObject.addProperty("postid", database.getPostByLength(dataMap.get("postid"),
                    dataMap.get("maxlength")).getPostId());
            dataObject.addProperty("postid", database.getPostByLength(dataMap.get("postid"),
                    dataMap.get("maxlength")).getUserId());
            dataObject.addProperty("postid", database.getPostByLength(dataMap.get("postid"),
                    dataMap.get("maxlength")).getPostData());
            data.add(dataObject);
            responseObject.add("data", data);
            return responseObject;
        }
    }
}
