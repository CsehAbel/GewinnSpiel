USE szavazas;
#SET GLOBAL general_log = 'ON';
#DROP TABLE IF EXISTS pontok;
CREATE TABLE pontok(
	adoszam VARCHAR(40) PRIMARY KEY NOT NULL,
    kapott int NOT NULL DEFAULT 0,
    szavazat int NOT NULL DEFAULT 0
);

#DROP TABLE IF EXISTS dolgozo;
CREATE TABLE dolgozo(
	adoszam VARCHAR(40) NOT NULL,
    torzsszam varchar(40)  PRIMARY KEY,
    nev varchar(250),
    uzemegyseg varchar(40),
    munkakor varchar(250)
);

CREATE TABLE dolgozo_duplicate(
	adoszam VARCHAR(40) NOT NULL,
    torzsszam varchar(40),
    nev varchar(250),
    uzemegyseg varchar(40),
    munkakor varchar(250)
);


#DROP TABLE dolgozo_replace;
CREATE TABLE dolgozo_replace(
    torzsszam varchar(40),
    ido datetime
);


DROP TRIGGER IF EXISTS ignr_dolgozo;
delimiter $$
CREATE TRIGGER ignr_dolgozo before insert on dolgozo_duplicate
for each row
begin
	if (new.torzsszam in (SELECT torzsszam FROM dolgozo)) THEN
		INSERT INTO dolgozo_replace SELECT adoszam,now() FROM dolgozo WHERE new.torzsszam=torzsszam;
	end if;
    REPLACE INTO dolgozo VALUES(new.adoszam,new.torzsszam,new.nev,new.uzemegyseg,new.munkakor);
end; $$


INSERT INTO dolgozo_duplicate VALUES(1,1,1,1,1);
INSERT INTO dolgozo_duplicate VALUES(2,1,1,1,1);
SELECT * FROM dolgozo;
SELECT * FROM dolgozo_replace;
SELECT * FROM dolgozo_duplicate;
#TRUNCATE TABLE dolgozo;
#TRUNCATE TABLE dolgozo_duplicate;
#TRUNCATE TABLE dolgozo_replace;
#TRUNCATE TABLE pontok;

/*JAVA ból meg kell hívni hogy kitöröljük időről időre*/
DROP PROCEDURE IF EXISTS torol_dolgozo_duplicate;
delimiter //
CREATE PROCEDURE torol_dolgozo_duplicate ()
BEGIN
 TRUNCATE TABLE dolgozo_duplicate;
END;
//
