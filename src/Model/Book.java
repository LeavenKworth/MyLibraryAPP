package Model;

public class Book {
    private int bookId;
    private int authorId;
    private String title;
    private int year;
    private int numberOfPages;
    private String cover;
    private String about;
    private String readStatus;
    private int rating;
    private String comments;
    private String releaseDate;


    public Book(int bookId, int authorId, String title, int year, int numberOfPages, String cover,
                String about, String readStatus, int rating, String comments, String releaseDate) {
        this.bookId = bookId;
        this.authorId = authorId;
        this.title = title;
        this.year = year;
        this.numberOfPages = numberOfPages;
        this.cover = cover;
        this.about = about;
        this.readStatus = readStatus;
        this.rating = rating;
        this.comments = comments;
        this.releaseDate = releaseDate;
    }


    public int getBookId() { return bookId; }
    public int getAuthorId() { return authorId; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public int getNumberOfPages() { return numberOfPages; }
    public String getCover() { return cover; }
    public String getAbout() { return about; }
    public String getReadStatus() { return readStatus; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }
    public String getReleaseDate() { return releaseDate; }
}