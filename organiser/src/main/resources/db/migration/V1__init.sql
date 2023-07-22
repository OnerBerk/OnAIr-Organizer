CREATE TABLE if not exists organiser_user(
    id bigserial  constraint pk_organiser_user primary key,
    version int  not null,
    created_at       timestamp not null,
    updated_at       timestamp not null,
    firstName text not null,
    lastName text not null,
    email text not null,
    password text not null
);