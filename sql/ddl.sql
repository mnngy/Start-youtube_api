create database youtube;

use youtube;

create table member
(
    memberIdx       int not null auto_increment primary key,
    memberId     varchar(20) not null,
    memberPassword varchar(20) not null
);