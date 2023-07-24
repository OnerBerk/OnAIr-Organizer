create table if not exists todo(
    id bigserial  constraint pk_todo primary key,
    version int  not null,
    created_at       timestamp not null,
    description text not null,
    state text not null,
    organiser_id bigint not null constraint fk_organiser references organiser_user (id) on delete cascade
);