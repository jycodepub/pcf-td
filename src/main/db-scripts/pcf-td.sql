create table if not exists hibernate_sequence
(
	next_val bigint null
)
engine=MyISAM
;

create table if not exists user
(
	id int not null
		primary key,
	password varchar(255) null,
	username varchar(255) null
)
engine=MyISAM
;

insert into hibernate_sequence (next_val) VALUES (1);