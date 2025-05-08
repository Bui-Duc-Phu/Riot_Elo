
DELIMITER //
CREATE FUNCTION get_newupdatestories_male()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 0
        ORDER BY s.updated_at DESC
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //get_newupdatestories_male

DELIMITER ;



DELIMITER //
CREATE FUNCTION get_newupdatestories_female()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 1
        ORDER BY s.updated_at DESC
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //get_newupdatestories_male
DELIMITER ;



DELIMITER //

CREATE FUNCTION get_topfavoritestories_male()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 0
        ORDER BY s.favorites_count DESC
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //

DELIMITER ;



DELIMITER //

CREATE FUNCTION get_topfavoritestories_female()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 1
        ORDER BY s.favorites_count DESC
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //

DELIMITER ;




DELIMITER //
CREATE FUNCTION get_completedStories_male()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 0 AND s.status_id = 1
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //
DELIMITER ;




DELIMITER //
CREATE FUNCTION get_completedStories_female()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 1 AND s.status_id = 1
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //
DELIMITER ;



DELIMITER //
CREATE FUNCTION get_TopViewCount_male()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 0
        ORDER BY s.views_count DESC
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //
DELIMITER ;






DELIMITER //
CREATE FUNCTION get_TopViewCount_female()
RETURNS JSON
DETERMINISTIC
BEGIN
    DECLARE result JSON;
    
    SET result = (
        SELECT JSON_ARRAYAGG(
            JSON_OBJECT(
                'storyId', s.story_id,
                'storyName', s.story_name,
                'authorName', a.author_name,
                'coverImage', s.cover_image,
                'genreNames', (
                    SELECT JSON_ARRAYAGG(g.genre_name)
                    FROM story_genres sg
                    JOIN genres g ON sg.genre_id = g.genre_id
                    WHERE sg.story_id = s.story_id
                ),
                'statusName', st.status_name,
                'sumChapter', (
                    SELECT COUNT(*) 
                    FROM chapters c 
                    WHERE c.story_id = s.story_id
                )
            )
        )
        FROM stories s
        JOIN authors a ON s.author_id = a.author_id
        JOIN status st ON s.status_id = st.status_id
        WHERE s.readmode = 1
        ORDER BY s.views_count DESC
    );
    
    RETURN IFNULL(result, JSON_ARRAY());
END //

DELIMITER ;

