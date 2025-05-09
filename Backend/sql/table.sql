CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    K_elo INT,
    LP INT,
    rank_lever VARCHAR(30),
    image_url VARCHAR(255) NULL
);


CREATE TABLE Matches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teams1 JSON NOT NULL,
    teams2 JSON NOT NULL,
    winner ENUM('teams1', 'teams2') NOT NULL,
    match_time VARCHAR(50) NULL
);


CREATE TABLE MatchInfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    match_id INT NOT NULL,
    user_id INT NOT NULL,
    champion VARCHAR(50) NOT NULL,
    position ENUM('Top', 'Jung', 'Mid', 'Ad', 'Support') NOT NULL,
    gold INT DEFAULT 0,
    kills INT DEFAULT 0,
    deaths INT DEFAULT 0,
    assists INT DEFAULT 0,
    FOREIGN KEY (match_id) REFERENCES Matches(id),
    FOREIGN KEY (user_id) REFERENCES User(id)
);




