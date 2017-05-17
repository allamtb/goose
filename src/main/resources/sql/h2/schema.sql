drop table if exists config;
drop table if exists opt_log;
drop table if exists moudal;

create table config (
	id bigint generated by default as identity,
	key varchar(128) not null,
	value varchar(255)
);

create table opt_log (
	id bigint generated by default as identity,
	moudlue int,
	requst varchar(2000000) not null,
	response varchar(2000000)
);

create table moudal (
	moudal_id INT,
	moudal_name varchar(255),
	primary key (moudal_id)
);