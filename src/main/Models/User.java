package main.Models;

public class User extends BaseModel {
    protected int id;
    private String fullName;
    private String login;
    private String password;
    private int role;

    public User(int id, String fullName, String login, String password, int role) {
        this.id = id;
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String fullName, String login, String password, int role) {
        this.fullName = fullName;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}

