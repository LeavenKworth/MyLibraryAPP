package Controller;

import Model.BookModel;
import Model.User;
import View.LoginView;
import View.Type1MainView;
import View.Type2MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginController {
    private LoginView view;
    private BookModel model;

    public LoginController(LoginView view, BookModel model) {
        this.view = view;
        this.model = model;
        initController();
    }

    private void initController() {
        view.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    User user = model.authenticateUser(view.getUsername(), view.getPassword());
                    if (user != null) {
                        view.dispose();
                        if (user.getUserType().equals("1")) {
                            Type1MainView type1View = new Type1MainView(user.getUsername());
                            new Type1MainController(type1View, model);
                            type1View.setVisible(true);
                        } else {
                            Type2MainView type2View = new Type2MainView(user.getUsername());
                            new Type2MainController(type2View, model);
                            type2View.setVisible(true);
                        }
                    } else {
                        view.showMessage("Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    view.showMessage("Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}