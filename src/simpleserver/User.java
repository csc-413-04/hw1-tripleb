package simpleserver;

public class User {
    private final String mUserName;
    private final String mUserId;


    public User (String userName, String userId) {
        mUserName = userName;
        mUserId = userId;
    }

    public String getUserName() {
        return mUserName;
    }

    public String getUserId() {
        return mUserId;
    }
}
