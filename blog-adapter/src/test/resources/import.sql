drop table if exists keyword cascade;
create table keyword
(
    keyword          varchar(255) not null,
    search_frequency integer,
    primary key (keyword)
);

insert into keyword
values ('대한민국', 42);
insert into keyword
values ('만세', 125);
insert into keyword
values ('연진아', 321);
insert into keyword
values ('기상캐스터', 4);
insert into keyword
values ('테니스', 12);
insert into keyword
values ('카카오', 1111);
insert into keyword
values ('골프', 123);
insert into keyword
values ('네이버', 63);
insert into keyword
values ('인생', 54);
insert into keyword
values ('세상만사', 92);
insert into keyword
values ('노노', 12);
insert into keyword
values ('마지막', 22);
