DROP DATABASE if exists nursing;
CREATE DATABASE nursing;
USE nursing;

SELECT FORMAT (getdate(), 'dd/MM/yyyy ') as date
GO

CREATE TABLE user(
ID integer auto_increment not null unique,
USERNAME varchar(20) not null,
PASSWORD varchar(16) not null,
EMAIL varchar(50) not null,
primary key (ID)
);

CREATE TABLE patient(
ID integer auto_increment not null unique,
NAME varchar(50) not null,
BIRTHDATE date not null,
ADDRESS varchar(100) not null,
VILLAGE varchar(50) not null,
PHONE int(9) not null,
EMAIL varchar(50) not null,
primary key (ID)
);

CREATE TABLE nursing(
ID integer auto_increment not null unique,
NURSING_DATE date not null,
MINIMUM_BLOOD_PRESSURE_MINIMUM int not null,
MAXIMUM_BLOOD_PRESSURE int not null,
HEART_BEAT int not null,
COMMENTS varchar(200) not null,
primary key (ID),
foreign key (PATIENT_ID) REFERENCES patient(ID)
);