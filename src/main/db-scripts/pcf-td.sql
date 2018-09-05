CREATE DATABASE pcf_td;
GRANT ALL ON pcf_td.* TO 'pcftd1'@'localhost' IDENTIFIED BY 'pcftd1';
GRANT ALL ON pcf_td.* TO 'pcftd1'@'%' IDENTIFIED BY 'pcftd1';

USE pcf_td;

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