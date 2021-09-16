create database tttbot_db;
create table player
(
    id       int,
    nickname varchar(60)      not null,
    points   bigint default 0 null
);

create unique index player_id_uindex
    on player (id);

create unique index player_nickname_uindex
    on player (nickname);

alter table player
    add constraint player_pk
        primary key (id);

alter table player
    modify id int auto_increment;
