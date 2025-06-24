package Controller;

import Model.Author;
import Model.Book;
import Model.BookModel;
import View.Type1MainView;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class Type1MainController {
    private Type1MainView view;
    private BookModel model;

    public Type1MainController(Type1MainView view, BookModel model) {
        this.view = view;
        this.model = model;
        initController();
        checkWishlistNotifications();
    }

    private void initController() {
        view.getAddButton().addActionListener(e -> addBook());
        view.getDeleteButton().addActionListener(e -> deleteBook());
        view.getDisplayButton().addActionListener(e -> displayBook());
        view.getSearchButton().addActionListener(e -> searchAuthor());
        view.getFavoriteBooksButton().addActionListener(e -> displayFavoriteBooks());
        view.getFavoriteAuthorsButton().addActionListener(e -> displayFavoriteAuthors());
        view.getUnreadBooksButton().addActionListener(e -> displayUnreadBooks());
        view.getShowCoverButton().addActionListener(e -> showBookCover());
    }

    private void addBook() {
        try {
            int authorId = model.addAuthor(view.getAuthorName(), view.getAuthorSurname());
            Book book = new Book(
                    0, // bookId is auto-generated
                    authorId,
                    view.getTitle(),
                    Integer.parseInt(view.getYear()),
                    Integer.parseInt(view.getPages()),
                    view.getCover(),
                    view.getAbout(),
                    view.getReadStatus(),
                    Integer.parseInt(view.getRating()),
                    view.getComments(),
                    view.getReleaseDate().isEmpty() ? null : view.getReleaseDate()
            );
            model.addBook(book);
            view.showMessage("Book added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException | NumberFormatException ex) {
            view.showMessage("Error adding book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteBook() {
        try {
            boolean success = model.deleteBook(Integer.parseInt(view.getDeleteBookId()));
            if (success) {
                view.showMessage("Book deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException ex) {
            view.showMessage("Error deleting book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayBook() {
        try {
            Book book = model.getBook(Integer.parseInt(view.getDisplayBookId()));
            if (book != null) {
                StringBuilder info = new StringBuilder();
                info.append("Title: ").append(book.getTitle()).append("\n");
                info.append("Year: ").append(book.getYear()).append("\n");
                info.append("Pages: ").append(book.getNumberOfPages()).append("\n");
                info.append("Cover: ").append(book.getCover()).append("\n");
                info.append("About: ").append(book.getAbout()).append("\n");
                info.append("Read Status: ").append(book.getReadStatus()).append("\n");
                info.append("Rating: ").append(book.getRating()).append("\n");
                info.append("Comments: ").append(book.getComments()).append("\n");
                info.append("Release Date: ").append(book.getReleaseDate());
                view.showMessage(info.toString(), "Book Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException ex) {
            view.showMessage("Error displaying book: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchAuthor() {
        try {
            List<Author> authors = model.searchAuthors(view.getSearchAuthorName());
            if (!authors.isEmpty()) {
                StringBuilder info = new StringBuilder();
                for (Author author : authors) {
                    info.append("ID: ").append(author.getAuthorId()).append("\n");
                    info.append("Name: ").append(author.getName()).append(" ").append(author.getSurname()).append("\n");
                    info.append("Website: ").append(author.getWebsite()).append("\n\n");
                }
                view.showMessage(info.toString(), "Author Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("No authors found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            view.showMessage("Error searching author: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayFavoriteBooks() {
        try {
            List<Book> books = model.getFavoriteBooks();
            if (!books.isEmpty()) {
                StringBuilder info = new StringBuilder();
                for (Book book : books) {
                    info.append("Title: ").append(book.getTitle()).append(", Rating: ").append(book.getRating()).append("\n");
                }
                view.showMessage(info.toString(), "Favorite Books", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("No favorite books found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            view.showMessage("Error displaying favorite books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayFavoriteAuthors() {
        try {
            List<Author> authors = model.getFavoriteAuthors();
            if (!authors.isEmpty()) {
                StringBuilder info = new StringBuilder();
                for (Author author : authors) {
                    info.append("Author: ").append(author.getName()).append(" ").append(author.getSurname()).append("\n");
                }
                view.showMessage(info.toString(), "Favorite Authors", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("No favorite authors found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            view.showMessage("Error displaying favorite authors: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void displayUnreadBooks() {
        try {
            List<Book> books = model.getUnreadBooks();
            if (!books.isEmpty()) {
                StringBuilder info = new StringBuilder();
                for (Book book : books) {
                    info.append("Title: ").append(book.getTitle()).append(", About: ").append(book.getAbout()).append("\n");
                }
                view.showMessage(info.toString(), "Unread Books", JOptionPane.INFORMATION_MESSAGE);
            } else {
                view.showMessage("No unread books found!", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            view.showMessage("Error displaying unread books: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showBookCover() {
        try {
            Book book = model.getBook(Integer.parseInt(view.getDisplayBookId()));
            if (book != null) {
                view.showCoverImage(book.getCover());
            } else {
                view.showMessage("Book not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException | NumberFormatException ex) {
            view.showMessage("Error displaying cover: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkWishlistNotifications() {
        try {
            List<Book> books = model.getWishlistNotifications();
            if (!books.isEmpty()) {
                StringBuilder notifications = new StringBuilder();
                for (Book book : books) {
                    notifications.append("Book: ").append(book.getTitle()).append(", Release Date: ").append(book.getReleaseDate()).append("\n");
                }
                view.showMessage(notifications.toString(), "Wishlist Notifications", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            view.showMessage("Error checking wishlist: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}