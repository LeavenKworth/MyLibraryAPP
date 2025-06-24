package Starter;

import Controller.LoginController;
import Model.BookModel;
import View.LoginView;

import javax.swing.*;

public class MyLibrary {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            BookModel bookModel = new BookModel();
            new LoginController(loginView, bookModel);
            loginView.setVisible(true);
        });
    }
}