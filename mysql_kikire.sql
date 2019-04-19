USE szavazas

SELECT * FROM dolgozo WHERE torzsszam = 99999

SHOW CREATE TABLE dolgozo;

/*Egy tábla amiben látszik ki kire szavazott,ki:torzsszam,kire:torzsszam*/
CREATE TABLE kikire(
	id int PRIMARY KEY auto_increment,
	ki varchar(40) NOT NULL,
    kire varchar(40) NOT NULL
)
/*Mindig amikor a szavazás megnyílik, ez törlődik-Tárolt eljárás*/

CREATE PROCEDURE trunc_kikire()
BEGIN
	TRUNCATE TABLE kikire;
END


UPDATE pontok SET szavazat=1 WHERE adoszam=(SELECT adoszam FROM dolgozo WHERE torzsszam = 55030);
SELECT * FROM pontok WHERE adoszam=(SELECT adoszam FROM dolgozo WHERE torzsszam = 55030);
SELECT ki,kire FROM kikire ORDER BY ki ASC;

SHOW CREATE PROCEDURE mukodik;
DROP PROCEDURE mukodik;

CREATE PROCEDURE `mukodik`()
BEGIN
INSERT INTO PONTOK(adoszam) VALUES("1997");
END

SELECT * FROM pontok ORDER BY adoszam ASC;
DELETE FROM pontok WHERE adoszam = 1997;

SELECT d.nev,d.torzsszam,SUM(p.kapott) as kap FROM pontok p JOIN dolgozo d ON p.adoszam=d.adoszam group by p.adoszam ORDER BY kap DESC