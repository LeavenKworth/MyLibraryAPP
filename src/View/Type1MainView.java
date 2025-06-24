package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Type1MainView extends JFrame {
    private JTextField bookIdField, titleField, yearField, pagesField, coverField, aboutField, readField, ratingField, commentsField, releaseDateField, authorNameField, authorSurnameField;
    private JTextField searchAuthorField, displayBookIdField, deleteBookIdField;
    private JButton addButton, deleteButton, displayButton, searchButton, favoriteBooksButton, favoriteAuthorsButton, unreadBooksButton, showCoverButton;

    public Type1MainView(String username) {
        setTitle("MyLibrary - Type-1 User: " + username);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Add Book Panel
        JPanel addBookPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        addBookPanel.add(new JLabel("Title:"), gbc);
        titleField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Year:"), gbc);
        yearField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(yearField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Pages:"), gbc);
        pagesField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(pagesField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Cover Path:"), gbc);
        coverField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(coverField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("About:"), gbc);
        aboutField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(aboutField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Read (1/2/3):"), gbc);
        readField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(readField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Rating (0-5):"), gbc);
        ratingField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(ratingField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Comments:"), gbc);
        commentsField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(commentsField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Release Date (YYYY-MM-DD):"), gbc);
        releaseDateField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(releaseDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Author Name:"), gbc);
        authorNameField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(authorNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        addBookPanel.add(new JLabel("Author Surname:"), gbc);
        authorSurnameField = new JTextField(20);
        gbc.gridx = 1;
        addBookPanel.add(authorSurnameField, gbc);

        addButton = new JButton("Add Book");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        addBookPanel.add(addButton, gbc);

        tabbedPane.addTab("Add Book", addBookPanel);

        // Delete Book Panel
        JPanel deleteBookPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        deleteBookPanel.add(new JLabel("Book ID:"), gbc);
        deleteBookIdField = new JTextField(20);
        gbc.gridx = 1;
        deleteBookPanel.add(deleteBookIdField, gbc);

        deleteButton = new JButton("Delete Book");
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        deleteBookPanel.add(deleteButton, gbc);

        tabbedPane.addTab("Delete Book", deleteBookPanel);

        // Display Book Panel
        JPanel displayBookPanel = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
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
        unreadBooksButton = new JButton("Show Unread Books");
        showCoverButton = new JButton("Show Book Cover");

        JPanel functionsPanel = new JPanel(new FlowLayout());
        functionsPanel.add(favoriteBooksButton);
        functionsPanel.add(favoriteAuthorsButton);
        functionsPanel.add(unreadBooksButton);
        functionsPanel.add(showCoverButton);

        add(tabbedPane, BorderLayout.CENTER);
        add(functionsPanel, BorderLayout.SOUTH);
    }

    // Getter methods for controller
    public String getTitle() { return titleField.getText(); }
    public String getYear() { return yearField.getText(); }
    public String getPages() { return pagesField.getText(); }
    public String getCover() { return coverField.getText(); }
    public String getAbout() { return aboutField.getText(); }
    public String getReadStatus() { return readField.getText(); }
    public String getRating() { return ratingField.getText(); }
    public String getComments() { return commentsField.getText(); }
    public String getReleaseDate() { return releaseDateField.getText(); }
    public String getAuthorName() { return authorNameField.getText(); }
    public String getAuthorSurname() { return authorSurnameField.getText(); }
    public String getSearchAuthorName() { return searchAuthorField.getText(); }
    public String getDisplayBookId() { return displayBookIdField.getText(); }
    public String getDeleteBookId() { return deleteBookIdField.getText(); }
    public JButton getAddButton() { return addButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public JButton getDisplayButton() { return displayButton; }
    public JButton getSearchButton() { return searchButton; }
    public JButton getFavoriteBooksButton() { return favoriteBooksButton; }
    public JButton getFavoriteAuthorsButton() { return favoriteAuthorsButton; }
    public JButton getUnreadBooksButton() { return unreadBooksButton; }
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