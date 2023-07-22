CREATE TABLE if not exists organiser_user(
    id bigserial  constraint pk_organiser_user primary key,
    version int  not null,
    created_at       timestamp not null,
    firstname text not null,
    lastname text not null,
    email text not null,
    password text not null
);