USE lokalis;
DROP TABLE IF EXISTS kep;
CREATE TABLE kep (
    id int(11) NOT NULL AUTO_INCREMENT,
    kep mediumblob NOT NULL,
    leiras varchar(100) NOT NULL,
    pont int(4) CHECK( pont >= 1 ),
    PRIMARY KEY (id)
);

DELIMITER //
CREATE PROCEDURE truncate_kep()
BEGIN
    TRUNCATE TABLE kep;
END//
DELIMITER ;

SELECT * FROM kep;