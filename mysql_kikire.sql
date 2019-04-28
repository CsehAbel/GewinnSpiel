USE lokalis;

/*Egy tábla amiben látszik ki kire szavazott,ki:torzsszam,kire:torzsszam*/
DROP TABLE IF EXISTS kikire;
CREATE TABLE kikire(
	id int PRIMARY KEY auto_increment,
	ki varchar(40) NOT NULL,
    kire varchar(40) NOT NULL
);
/*Mindig amikor a szavazás megnyílik, ez törlődik-Tárolt eljárás*/

DROP PROCEDURE IF EXISTS trunc_kikire;
delimiter $$
CREATE PROCEDURE trunc_kikire()
BEGIN
	TRUNCATE TABLE kikire;
END
$$
delimiter ;

DROP PROCEDURE if exists `szavazatEgy`;
delimiter $$
CREATE PROCEDURE `szavazatEgy`()
BEGIN
UPDATE pontok SET szavazat=1;
END
$$
delimiter ;
SELECT * FROM pontok ORDER BY adoszam ASC;

SELECT d.nev,d.torzsszam,SUM(p.kapott) as kap FROM pontok p JOIN dolgozo d ON p.adoszam=d.adoszam group by p.adoszam ORDER BY kap DESC