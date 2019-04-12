USE lokalis;
CREATE TABLE levon (
	id int AUTO_INCREMENT,
    ftorzsszam nvarchar(40),
    levontpont int(11),
    kitol nvarchar(40),
    dat TIMESTAMP,
    primary key (id)
)