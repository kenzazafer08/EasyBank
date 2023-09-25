create table person
(
    id         serial
        primary key,
    first_name varchar(255),
    last_name  varchar(255),
    phone      varchar(20),
    address    text
);

alter table person
    owner to postgres;

create table client
(
    code      varchar(10) not null
        primary key,
    person_id integer
        references person
);

alter table client
    owner to postgres;

create table employee
(
    number           varchar(10) not null
        constraint employee_number_pk
            primary key,
    recruitment_date date,
    email            varchar(255),
    person_id        integer
        references person
);

alter table employee
    owner to postgres;

create table account
(
    number         varchar(20) not null
        primary key
        unique,
    sold           double precision,
    creation_date  date,
    state          varchar(10)
        constraint check_state
            check ((state)::text = ANY ((ARRAY ['active'::character varying, 'inactive'::character varying])::text[])),
    client_code    varchar(20)
        references client,
    employe_number varchar(20)
        constraint account_employee_number_fkey
            references employee
);

alter table account
    owner to postgres;

create table currentaccount
(
    id             serial
        primary key,
    overdraft      double precision,
    account_number varchar(255)
        constraint account_code_fkey
            references account
);

alter table currentaccount
    owner to postgres;

create table savingaccount
(
    id             serial
        primary key,
    interest_rate  double precision,
    account_number varchar(255)
        constraint account_code_fkey
            references account
);

alter table savingaccount
    owner to postgres;

create table operation
(
    id              serial
        primary key,
    number          varchar(20),
    creation_date   date,
    amount          double precision,
    type            varchar(20)
        constraint check_type
            check ((type)::text = ANY
                   ((ARRAY ['payment'::character varying, 'withdrawal'::character varying])::text[])),
    employee_number varchar(20)
        references employee,
    account_number  varchar(255)
        constraint account_code_fkey
            references account
);

alter table operation
    owner to postgres;

create table mission
(
    id          serial
        primary key,
    code        varchar(20)
        unique,
    name        varchar(255),
    description text
);

alter table mission
    owner to postgres;

create table affectation
(
    id              serial
        primary key,
    start_date      date,
    end_date        date,
    mission_id      integer
        references mission,
    employee_number varchar(20)
        references employee
);

alter table affectation
    owner to postgres;


