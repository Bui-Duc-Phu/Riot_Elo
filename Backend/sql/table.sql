CREATE TABLE authors (
    author_id VARCHAR(255) PRIMARY KEY,
    author_name VARCHAR(255) 
);

CREATE TABLE genres (
    genre_id VARCHAR(255) PRIMARY KEY,
    genre_name VARCHAR(255)
);


CREATE TABLE status (
    status_id INT PRIMARY KEY,
    status_name VARCHAR(255)
);


CREATE TABLE users (
    user_id VARCHAR(255) PRIMARY KEY,
    name VARCHAR(255) ,
    image VARCHAR(512)
);

CREATE TABLE stories (
    story_id VARCHAR(255) PRIMARY KEY,
    story_name VARCHAR(255) ,
    author_id VARCHAR(255),
    status_id INT,
    description TEXT,
    cover_image VARCHAR(512),
    views_count INT DEFAULT 0,
    readmode INT DEFAULT 0,
    favorites_count INT DEFAULT 0,
    follows_count INT DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (author_id) REFERENCES authors(author_id) ON DELETE CASCADE,
    FOREIGN KEY (status_id) REFERENCES status(status_id) ON DELETE RESTRICT
);


CREATE TABLE chapters (
    chapter_id INT AUTO_INCREMENT PRIMARY KEY,
    chapter_number INT ,
    chapter_title VARCHAR(255) ,
    story_id VARCHAR(255),
    content TEXT,
    crawled_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE
);

CREATE TABLE story_genres (
    story_id VARCHAR(255),
    genre_id VARCHAR(255),
    PRIMARY KEY (story_id, genre_id),
    FOREIGN KEY (story_id) REFERENCES stories(story_id) ON DELETE CASCADE,
    FOREIGN KEY (genre_id) REFERENCES genres(genre_id) ON DELETE CASCADE
);








