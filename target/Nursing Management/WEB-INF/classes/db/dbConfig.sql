DROP DATABASE if exists nursing;
CREATE DATABASE nursing;
USE nursing;

CREATE TABLE users(
ID integer auto_increment not null unique,
NAME varchar(50) not null,
PASSWORD varchar(50) not null,
EMAIL varchar(50) not null,
primary key (ID)
);

CREATE TABLE patients(
ID integer auto_increment not null unique,
NAME varchar(50) not null,
BIRTHDATE DATE not null,
ADDRESS varchar(100) not null,
VILLAGE varchar(50) not null,
PHONE integer(9) not null,
EMAIL varchar(50) not null,
primary key (ID)
);

CREATE TABLE acts(
ID integer auto_increment not null unique,
PATIENT_ID integer not null,
NURSING_DATE DATE not null,
MINIMUM_BLOOD_PRESSURE integer not null,
MAXIMUM_BLOOD_PRESSURE integer not null,
HEART_BEAT integer not null,
COMMENTS varchar(200) not null,
PRIMARY KEY (ID),
FOREIGN KEY (PATIENT_ID) REFERENCES patients(ID)
);