CREATE DATABASE IF NOT EXISTS mylibrary;
USE mylibrary;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    user_type ENUM('1', '2') NOT NULL
);

CREATE TABLE authors (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    website VARCHAR(100)
);

CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    author_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    year INT,
    number_of_pages INT,
    cover VARCHAR(255),
    about TEXT,
    read_status ENUM('1', '2', '3') NOT NULL,
    rating INT CHECK (rating >= 0 AND rating <= 5),
    comments TEXT,
    release_date DATE,
    FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE
);

-- Insert sample users
INSERT INTO users (username, password, user_type) VALUES
('admin', 'admin123', '1'),
('user', 'user123', '2');

-- Insert sample authors
INSERT INTO authors (name, surname, website) VALUES
('John', 'Doe', 'website-1'),
('Jane', 'Smith', 'website-2');

-- Insert sample books
INSERT INTO books (author_id, title, year, number_of_pages, cover, about, read_status, rating, comments, release_date) VALUES
(1, 'Sample Book 1', 2020, 300, 'Book1.jpg', 'A thrilling adventure.', '1', 4, 'Great read!', NULL),
(1, 'Sample Book 2', 2021, 250, 'Book2.jpg', 'A mystery novel.', '1', 5, 'Loved it!', NULL),
(1, 'Sample Book 3', 2022, 400, 'Book3.jpg', 'A sci-fi epic.', '1', 4, 'Amazing!', NULL),
(2, 'Sample Book 4', 2023, 350, 'Book4.jpg', 'A romantic story.', '3', 0, NULL, '2025-06-30');