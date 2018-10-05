package simpleserver;

public class Post {
    private final String mUserId;
    private final String mPostId;
    private final String mPostData;


    public Post (String userId, String postId, String postData) {
        mUserId = userId;
        mPostId = postId;
        mPostData = postData;
    }

    public String getUserId() {
        return mUserId;
    }

    public String getPostId() {
        return mPostId;
    }
    public String getPostData() {
        return mPostData;
    }
}
