

DROP TABLE IF EXISTS dolgozo;
CREATE TABLE dolgozo(
	adoszam VARCHAR(40) NOT NULL,
    torzsszam varchar(40)  PRIMARY KEY,
    nev varchar(250),
    uzemegyseg varchar(40),
    munkakor varchar(250),
    adminn int DEFAULT 0
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
    INSERT IGNORE INTO dolgozo(adoszam,torzsszam,nev,uzemegyseg,munkakor) VALUES(new.adoszam,new.torzsszam,new.nev,new.uzemegyseg,new.munkakor);
end; $$

/*
INSERT INTO dolgozo_duplicate VALUES(1,1,1,1,1);
INSERT INTO dolgozo_duplicate VALUES(2,1,1,1,1);
SELECT * FROM dolgozo;
SELECT * FROM dolgozo_replace;
SELECT * FROM dolgozo_duplicate WHERE adoszam=8473290267;*/

#TRUNCATE TABLE dolgozo;
#TRUNCATE TABLE dolgozo_duplicate;
#TRUNCATE TABLE dolgozo_replace;
#TRUNCATE TABLE pontok;

/*TRUNCATE dolgozo,dolgozo_replace,dolgozo_duplicate egy tárol eljárást meghívni az mssql anonymous tárolt eljárás előtt*/
/*JAVA ból meg kell hívni hogy kitöröljük időről időre*/
DROP PROCEDURE IF EXISTS torol_dolgozo_duplicate;
delimiter //
CREATE PROCEDURE torol_dolgozo_duplicate ()
BEGIN
 TRUNCATE TABLE dolgozo_duplicate;
 TRUNCATE TABLe dolgozo_replace;
END//
delimiter ;
