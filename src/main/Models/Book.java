package main.Models;

public class Book extends BaseModel{
    private String name;
    private String authorFullName ;
    private int countOfCopies;

    public Book(String name, String authorFullName, int countOfCopies) {
        this.name = name;
        this.authorFullName = authorFullName;
        this.countOfCopies = countOfCopies;
    }


    public Book(int id, String name, String authorFullName, int countOfCopies) {
        this.id = id;
        this.name = name;
        this.authorFullName = authorFullName;
        this.countOfCopies = countOfCopies;
    }
    public Book() { }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }

    public int getCountOfCopies() {
        return countOfCopies;
    }

    public void setCountOfCopies(int countOfCopies) {
        this.countOfCopies = countOfCopies;
    }
}
