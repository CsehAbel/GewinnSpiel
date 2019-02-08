USE lokalis;
#SET GLOBAL general_log = 'ON';
DROP TABLE IF EXISTS pontok;
CREATE TABLE pontok(
	adoszam VARCHAR(40) PRIMARY KEY NOT NULL,
    kapott int NOT NULL DEFAULT 0,
    szavazat int NOT NULL DEFAULT 0
);
/*DROP TABLE pontok_duplicate;*/
CREATE TABLE pontok_duplicate(
	adoszam VARCHAR(40) NOT NULL,
    kapott int NOT NULL DEFAULT 0,
    szavazat int NOT NULL DEFAULT 0
);

/*
DROP TRIGGER IF EXISTS ignr_meglevo_adoszam;
 */

delimiter $$
CREATE TRIGGER ignr_meglevo_adoszam BEFORE INSERT ON pontok_duplicate
for each row
begin
	INSERT IGNORE INTO pontok(adoszam) VALUES(new.adoszam);
end$$
delimiter ;

SELECT * FROM pontok_duplicate;
SELECT * FROM pontok;

/*DROP PROCEDURE IF EXISTS truncate_pontok;*/
DELIMITER //
CREATE PROCEDURE truncate_pontok_duplicate()
BEGIN
    TRUNCATE TABLE pontok_duplicate;
END//
DELIMITER ;

CALL truncate_pontok();


