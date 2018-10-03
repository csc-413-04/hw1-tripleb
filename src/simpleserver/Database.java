package simpleserver;

import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {
    private HashMap<String, User> userHashMap = new HashMap<>();
    private HashMap<String, Post> postHashMap = new HashMap<>();

<<<<<<< HEAD
    public Database (String fileExtension) throws FileNotFoundException, UnsupportedEncodingException {
        InputStream inputStream = new FileInputStream(fileExtension);
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonRootObject = (JsonObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
=======
    private static Database instance;

    private Database () {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("data.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonRootObject = null;
        try {
            jsonRootObject = (JsonObject) jsonParser.parse(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
>>>>>>> master
        JsonArray usersArray = jsonRootObject.getAsJsonArray("users");
        JsonArray postsArray = jsonRootObject.getAsJsonArray("posts");

        for (JsonElement user : usersArray) {
            JsonObject userObject = user.getAsJsonObject();
<<<<<<< HEAD
            String userName = userObject.get("userid").getAsString();
            String userId = userObject.get("username").getAsString();
=======
            String userId = userObject.get("userid").getAsString();
            String userName = userObject.get("username").getAsString();
>>>>>>> master
            User newUser = new User(userName, userId);
            userHashMap.put(userId, newUser);
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

<<<<<<< HEAD
=======
    public static synchronized Database getInstance() {
        if(instance == null){
            instance = new Database();
        }
        return instance;
    }

>>>>>>> master
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
<<<<<<< HEAD
        if (postHashMap.get(postId) != null && postHashMap.get(postId).getPostData().length() <= Integer.parseInt(maxLength)) {
            return postHashMap.get(postId);
        }
        return null;
=======
        if (postHashMap.get(postId).getPostData().length() < 100) {
            return postHashMap.get(postId);
        } else {
            return null;
        }
>>>>>>> master
    }
}
