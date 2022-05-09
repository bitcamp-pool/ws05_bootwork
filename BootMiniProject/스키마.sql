/*mysql 5 버전*/
grant all privileges on *.* to 'angel'@'%' identified by '1234' with grant option;

/*mysql 8 버전*/
create user 'angel'@'%'identified by '1234';
grant all privileges on *.* to 'angel'@'%'

create table member(
	num smallint auto_increment primary key,
	name varchar(20),
	id varchar(20),
	pass varchar(20),
	hp varchar(20),
	email varchar(50),
	addr varchar(300),
	gaipday datetime
);
select * from `member` m ;
delete from member where num = 1;
