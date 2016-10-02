# --- First database schema - HSQL

# --- !Ups

set ignorecase true;

create table servers (
  id                        bigint generated by default as identity(start with 1000) not null,
  name                      varchar(255) not null,
  comment                   varchar(255),
  status                    int not null,
  -- type is 0 (public), 1 (group), 2 (private)
  port                      int not null,
  authorized_keys_path      varchar(255),
  constraint pk_servers primary key (id))
;

create sequence servers_seq start with 1000;

create table profiles (
  id                        bigint generated by default as identity(start with 1000) not null,
  name                      varchar(255) not null,
  login                     varchar(255),
  constraint pk_profiles primary key (id))
;

create sequence profiles_seq start with 1000;

create table environments (
  id                        bigint generated by default as identity(start with 1000) not null,
  name                      varchar(255) not null,
  description               varchar(255),
  constraint pk_environments primary key (id))
;

create sequence environments_seq start with 1000;

create table keys (
  id                        bigint generated by default as identity(start with 1000) not null,
  name                      varchar(255) not null,
  fingerprint               varchar(255),
  public_key                varchar(255),
  enbabled                  boolean,
  constraint pk_keys primary key (id))
;

create sequence keys_seq start with 1000;


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists servers;
drop table if exists profiles;
drop table if exists environments;
drop table if exists keys;


SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists servers_seq;
drop sequence if exists profiles_seq;
drop sequence if exists environments_seq;
drop sequence if exists keys_seq;