package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Type2MainView extends JFrame {
    private JTextField searchAuthorField, displayBookIdField;
    private JButton displayButton, searchButton, favoriteBooksButton, favoriteAuthorsButton, showCoverButton;

    public Type2MainView(String username) {
        setTitle("MyLibrary - Type-2 User: " + username);
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Display Book Panel
        JPanel displayBookPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        displayBookPanel.add(new JLabel("Book ID:"), gbc);
        displayBookIdField = new JTextField(20);
        gbc.gridx = 1;
        displayBookPanel.add(displayBookIdField, gbc);

        displayButton = new JButton("Display Book");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        displayBookPanel.add(displayButton, gbc);

        tabbedPane.addTab("Display Book", displayBookPanel);

        // Search Author Panel
        JPanel searchAuthorPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchAuthorPanel.add(new JLabel("Author Name:"), gbc);
        searchAuthorField = new JTextField(20);
        gbc.gridx = 1;
        searchAuthorPanel.add(searchAuthorField, gbc);

        searchButton = new JButton("Search Author");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        searchAuthorPanel.add(searchButton, gbc);

        tabbedPane.addTab("Search Author", searchAuthorPanel);

        // Other functions
        favoriteBooksButton = new JButton("Show Favorite Books");
        favoriteAuthorsButton = new JButton("Show Favorite Authors");
        showCoverButton = new JButton("Show Book Cover");

        JPanel functionsPanel = new JPanel(new FlowLayout());
        functionsPanel.add(favoriteBooksButton);
        functionsPanel.add(favoriteAuthorsButton);
        functionsPanel.add(showCoverButton);

        add(tabbedPane, BorderLayout.CENTER);
        add(functionsPanel, BorderLayout.SOUTH);
    }

    // Getter methods for controller
    public String getSearchAuthorName() { return searchAuthorField.getText(); }
    public String getDisplayBookId() { return displayBookIdField.getText(); }
    public JButton getDisplayButton() { return displayButton; }
    public JButton getSearchButton() { return searchButton; }
    public JButton getFavoriteBooksButton() { return favoriteBooksButton; }
    public JButton getFavoriteAuthorsButton() { return favoriteAuthorsButton; }
    public JButton getShowCoverButton() { return showCoverButton; }

    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public void showCoverImage(String coverPath) {
        File file = new File("C:\\Users\\ASUS\\Desktop\\MyLibraryAPP\\src\\Covers\\"+coverPath);
        if (file.exists()) {
            JFrame coverFrame = new JFrame("Book Cover");
            coverFrame.setSize(400, 400);
            coverFrame.setLocationRelativeTo(null);
            JLabel coverLabel = new JLabel(new ImageIcon("C:\\Users\\ASUS\\Desktop\\MyLibraryAPP\\src\\Covers\\"+coverPath));
            coverFrame.add(coverLabel);
            coverFrame.setVisible(true);
        } else {
            showMessage("Image file not found: " + coverPath, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}