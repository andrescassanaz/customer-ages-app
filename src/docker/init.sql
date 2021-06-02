create table client
(
    id  serial not null constraint clients_pk primary key,
    first_name text,
    last_name  text,
    age        integer,
    birthdate  date
);

SET search_path = public