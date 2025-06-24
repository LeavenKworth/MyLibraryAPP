package Model;

public class User {
    private int userId;
    private String username;
    private String password;
    private String userType;

    // Constructor
    public User(int userId, String username, String password, String userType) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userType = userType;
    }

    // Getters
    public int getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getUserType() { return userType; }
}