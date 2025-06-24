package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookModel {
    public User authenticateUser(String username, String password) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"), rs.getString("user_type"));
            }
            return null;
        }
    }

    public int addAuthor(String name, String surname) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT author_id FROM authors WHERE name = ? AND surname = ?");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("author_id");
            } else {
                PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO authors (name, surname, website) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, name);
                insertStmt.setString(2, surname);
                insertStmt.setString(3, "website-" + (getMaxAuthorId(conn) + 1));
                insertStmt.executeUpdate();
                rs = insertStmt.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        }
    }

    private int getMaxAuthorId(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(author_id) FROM authors");
        rs.next();
        return rs.getInt(1);
    }

    public void addBook(Book book) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO books (author_id, title, year, number_of_pages, cover, about, read_status, rating, comments, release_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setInt(1, book.getAuthorId());
            stmt.setString(2, book.getTitle());
            stmt.setInt(3, book.getYear());
            stmt.setInt(4, book.getNumberOfPages());
            stmt.setString(5, book.getCover());
            stmt.setString(6, book.getAbout());
            stmt.setString(7, book.getReadStatus());
            stmt.setInt(8, book.getRating());
            stmt.setString(9, book.getComments());
            stmt.setString(10, book.getReleaseDate());
            stmt.executeUpdate();
        }
    }

    public boolean deleteBook(int bookId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM books WHERE book_id = ?");
            stmt.setInt(1, bookId);
            return stmt.executeUpdate() > 0;
        }
    }

    public Book getBook(int bookId) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT b.*, a.name, a.surname FROM books b JOIN authors a ON b.author_id = a.author_id WHERE b.book_id = ?")) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("book_id"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getInt("number_of_pages"),
                        rs.getString("cover"),
                        rs.getString("about"),
                        rs.getString("read_status"),
                        rs.getInt("rating"),
                        rs.getString("comments"),
                        rs.getString("release_date")
                );
            }
            return null;
        }
    }

    public List<Author> searchAuthors(String name) throws SQLException {
        List<Author> authors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM authors WHERE name LIKE ?")) {
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                ));
            }
            return authors;
        }
    }

    public List<Book> getFavoriteBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE read_status = '1' AND rating >= 4")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getInt("number_of_pages"),
                        rs.getString("cover"),
                        rs.getString("about"),
                        rs.getString("read_status"),
                        rs.getInt("rating"),
                        rs.getString("comments"),
                        rs.getString("release_date")
                ));
            }
            return books;
        }
    }

    public List<Author> getFavoriteAuthors() throws SQLException {
        List<Author> authors = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT a.author_id, a.name, a.surname, a.website, COUNT(b.book_id) as book_count " +
                             "FROM authors a JOIN books b ON a.author_id = b.author_id " +
                             "GROUP BY a.author_id HAVING book_count >= 3")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                authors.add(new Author(
                        rs.getInt("author_id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("website")
                ));
            }
            return authors;
        }
    }

    public List<Book> getUnreadBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books WHERE read_status = '2'")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getInt("number_of_pages"),
                        rs.getString("cover"),
                        rs.getString("about"),
                        rs.getString("read_status"),
                        rs.getInt("rating"),
                        rs.getString("comments"),
                        rs.getString("release_date")
                ));
            }
            return books;
        }
    }

    public List<Book> getWishlistNotifications() throws SQLException {
        List<Book> books = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM books WHERE read_status = '3' AND release_date <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)")) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getInt("author_id"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getInt("number_of_pages"),
                        rs.getString("cover"),
                        rs.getString("about"),
                        rs.getString("read_status"),
                        rs.getInt("rating"),
                        rs.getString("comments"),
                        rs.getString("release_date")
                ));
            }
            return books;
        }
    }
}