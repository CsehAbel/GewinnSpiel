USE lokalis;
DROP TABLE IF EXISTS levon;
CREATE TABLE levon (
	id int AUTO_INCREMENT,
    ftorzsszam char(40),
    levontpont int(11),
    kitol char(40),
    dat TIMESTAMP,
    primary key (id)
)