package simpleserver;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<String, Post> postHashMap = new HashMap<>();

    public Database (String fileExtension) throws FileNotFoundException, UnsupportedEncodingException {
        InputStream inputStream = new FileInputStream(fileExtension);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonRootObject = (JsonObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
        JsonArray usersArray = jsonRootObject.getAsJsonArray("users");
        JsonArray postsArray = jsonRootObject.getAsJsonArray("posts");

        for (JsonElement user : usersArray) {
            JsonObject userObject = user.getAsJsonObject();
            String userName = userObject.get("userid").getAsString();
            String userId = userObject.get("username").getAsString();
            User newUser = new User(userName, userId);
            userHashMap.put(userId, newUser);
            System.out.println(userHashMap.get(userId).getUserName()); //just to test and see
        }

        for (JsonElement post : postsArray) {
            JsonObject postObject = post.getAsJsonObject();
            String userId = postObject.get("userid").getAsString();
            String postId = postObject.get("postid").getAsString();
            String postData = postObject.get("data").getAsString();
            Post newPost = new Post(userId, postId, postData);
            postHashMap.put(postId, newPost);
        }
    }

    public ArrayList<User> getAllUsers() {
        // returns ArrayList of all users as ArrayList of User objects
        ArrayList userList = new ArrayList();
        for (String key : userHashMap.keySet()) {
            userList.add(userHashMap.get(key));
        }
        return userList;
    }

    public User getUser(String userId) {
        //returns desired user
        return userHashMap.get(userId);
    }
    public Post getPost(String postId) {
        // retuns desired post
        return postHashMap.get(postId);
    }
    public Post getPostByLength(String postId, String maxLength) {
        // returns Post object or null per parammeters
        if (postHashMap.get(postId) != null && postHashMap.get(postId).getPostData().length() <= Integer.parseInt(maxLength)) {
            return postHashMap.get(postId);
        }
        return null;
    }
}
