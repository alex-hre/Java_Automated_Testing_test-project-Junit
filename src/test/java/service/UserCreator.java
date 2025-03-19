package service;

import model.User;


/**Utility class for creating test users with different credentials.*/
public class UserCreator {

    public static final String USER_NAME = "random_smb";
    public static final String USER_PASSWORD = "random_smt";

    /**Different options to create Users*/
    public static User emptyUser(){
        return new User("", "");
    }

    public static User withEmptyUsername(){
        return new User(USER_NAME, "");
    }

    public static User withEmptyPassword(){
        return new User("", USER_PASSWORD);
    }

    public static User withCustomCredentials(String username, String password) {
        return new User(username, password);
    }

}
