create table user
(
    id       serial                 not null,
    email    character varying(128) not null,
    password character varying(256) not null,
    primary key (id)
);

create table movie
(
    id           serial                 not null,
    name         character varying(256) not null,
    resume       text,
    duration     int,
    release_date date,
    language     character varying(64),
    access_type  character varying(10)  not null default 'PUBLIC',
    user_id      int                    not null,
    primary key (id),
    constraint fk_user_movie foreign key (user_id) references user (id)
);

