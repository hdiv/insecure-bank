DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS cashaccount;
DROP TABLE IF EXISTS creditaccount;
DROP TABLE IF EXISTS transfer;
DROP TABLE IF EXISTS transaction;

create table account (
    username varchar(80) not null,
    name varchar(80) not null,
    surname varchar(80) not null,
    password varchar(80) not null,
    constraint pk_account primary key (username)
);

create table cashaccount (
    id int not null,
    number varchar(80) not null,
    username varchar(80)  not null,
    availablebalance double precision,
    description varchar(80)  not null,
    constraint pk_cashaccount primary key (id)
);

create table creditaccount(
    id int not null,
    number varchar(80) not null,
    username varchar(80)  not null,
    description varchar(80)  not null,
    availablebalance double precision,
    cashaccountid int,
    constraint pk_creditaccount primary key (id)
);


create table transfer(
    id int generated always as identity not null,
    fromAccount varchar(80) not null,
    toAccount varchar(80)  not null,
    description varchar(80)  not null,
    amount double precision,
    fee double precision,
    username varchar(80)  not null,
    date TIMESTAMP,
    constraint pk_transfer primary key (id)
);

create table transaction(
    id int generated always as identity not null,
    date TIMESTAMP,
    description varchar(80)  not null,
    number varchar(80) not null,
    amount double precision,
    availablebalance double precision,
    constraint pk_transaction primary key (id)
);