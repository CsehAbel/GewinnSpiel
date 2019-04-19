USE szavazas;
SELECT users_0.CODE AS 'DOLGOZOKOD', users_0.NAME AS 'DOLGOZONEV', card_0.NUMBER AS 'KARTYASZAM'
FROM xsw_ziehl.card card_0, xsw_ziehl.users users_0
WHERE card_0.USER_ID = users_0.ID;

DROP TABLE IF EXISTS xsw;
CREATE TABLE xsw(
	kartyaszam int(11) DEFAULT NULL,/*kartyaszam card.NUMBER*/
    dolgozokod bigint(20) PRIMARY KEY,/*dolgozokod users.CODE*/
    nev varchar(255) DEFAULT NULL/*név users.NAME*/
);

DROP TABLE IF EXISTS xsw_duplicate;
CREATE TABLE xsw_duplicate(
	kartyaszam int(11) DEFAULT NULL,/*kartyaszam card.NUMBER*/
    dolgozokod bigint(20),/*dolgozokod users.CODE*/
    nev varchar(255) DEFAULT NULL/*név users.NAME*/
);


DROP TRIGGER IF EXISTS ignr_meglevo_xsw;


delimiter $$
CREATE TRIGGER ignr_meglevo_xsw BEFORE INSERT ON xsw_duplicate
for each row
begin
	INSERT IGNORE INTO xsw(kartyaszam,dolgozokod,nev) VALUES(new.kartyaszam,new.dolgozokod,new.nev);
end$$
delimiter ;


/*DROP PROCEDURE IF EXISTS truncate_pontok;*/
DELIMITER //
CREATE PROCEDURE truncate_xsw_duplicate()
BEGIN
    TRUNCATE TABLE xsw_duplicate;
END//
DELIMITER ;

CALL truncate_xsw_duplicate();

SELECT * FROM xsw;
SELECT * FROM xsw_duplicate;