package Model;

public class Author {
    private int authorId;
    private String name;
    private String surname;
    private String website;

    // Constructor
    public Author(int authorId, String name, String surname, String website) {
        this.authorId = authorId;
        this.name = name;
        this.surname = surname;
        this.website = website;
    }

    // Getters
    public int getAuthorId() { return authorId; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getWebsite() { return website; }
}