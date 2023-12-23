CREATE TABLE users
(
    id         bigserial primary key,
    first_name varchar,
    last_name  varchar,
    login      varchar,
    password   varchar
);

CREATE TABLE task
(
    id          bigserial primary key,
    title       varchar,
    description varchar,
    completed   boolean,
    user_id     bigint references users,
    start_date  timestamp,
    due_date    timestamp
);

