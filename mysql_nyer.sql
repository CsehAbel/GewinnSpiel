USE lokalis;

CREATE TABLE nyer (
	id int(11) not null auto_increment,
	torzsszam varchar(40),
    dat timestamp,
    dpont int(4),
    nyid int(4),
    nypont int(4),
    primary key (id),
    foreign key (nyid) REFERENCES kep(id)
)