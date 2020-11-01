package main.Models;

public class Reader extends BaseModel{
    private String fullName;
    private String group;

    public Reader(String fullName, String group) {
        this.fullName = fullName;
        this.group = group;
    }


    public Reader() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
