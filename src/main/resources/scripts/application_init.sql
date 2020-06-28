DROP DATABASE IF EXISTS car_rental;

CREATE DATABASE car_rental
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

# command line runner

USE car_rental;

create table rental_config(
                         id bigint not null auto_increment,
                         address_line varchar(255),
                         city varchar(255),
                         country varchar(255),
                         domain varchar(255),
                         name varchar(255),
                         nip varchar(255),
                         owner varchar(255),
                         zip_code varchar(255),
                         primary key (id)
);

INSERT INTO rental_config (name, domain, owner, nip, country, city, address_line, zip_code)
VALUES ('Name', 'www.website.com',
        'owner',
        '000-00-00-000', 'Country', 'City','ul. adres 0/00','00-000');

create table users (
                       id bigint not null auto_increment,
                       active bit not null,
                       password varchar(255) not null,
                       username varchar(255) not null,
                       primary key (id)
);

INSERT INTO users (active, password, username) VALUES (true, '{noop}123', 'admin');

create table roles (
                       id bigint not null auto_increment,
                       name varchar(255),
                       primary key (id)
);

INSERT INTO roles(name) VALUES ('ROLE_ADMIN');
INSERT INTO roles(name) VALUES ('ROLE_MANAGER');
INSERT INTO roles(name) VALUES ('ROLE_EMPLOYEE');

create table sipp_codes(
                        id bigint not null auto_increment,
                        code varchar(255),
                        deposit bigint(20),
                        per_day_charge bigint(20),
                        primary key (id)
);

INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('MBMR', 800, 120);

INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('CDAR', 1200, 200);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('CDMR', 1200, 180);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('CWAR', 1200, 220);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('CWMR', 1200, 210);

INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('IDAR', 1500, 250);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('IDMR', 1500, 230);

INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('SDAR', 2000, 290);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('SDMR', 2000, 270);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('SWAR', 2000, 310);
INSERT INTO sipp_codes(code, deposit, per_day_charge) VALUES ('SWMR', 2000, 300);

create table statuses(
                     id bigint not null auto_increment,
                     name varchar(255),
                     pl_description varchar(255),
                     primary key (id)
);

INSERT INTO statuses(name, pl_description)VALUES ('available', 'dostępny');
INSERT INTO statuses(name, pl_description)VALUES ('rented', 'wynajęty');
INSERT INTO statuses(name, pl_description)VALUES ('inRepair', 'w naprawie');
INSERT INTO statuses(name, pl_description)VALUES ('onInspection', 'przegląd');
INSERT INTO statuses(name, pl_description)VALUES ('damaged', 'uszkodzony');
INSERT INTO statuses(name, pl_description)VALUES ('off', 'wyłączony z użytku');
