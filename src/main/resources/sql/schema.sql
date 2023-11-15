drop
    database if exists channelingCenter;
create
    database if not exists channelingCenter;

use
    channelingCenter;

create table patient
(
    patient_id    varchar(10) primary key,
    patient_name  varchar(25) not null,
    mobile_number varchar(10) not null,
    address       text        not null,
    sex           varchar(10) not null,
    email         varchar(25) not null,
    age           varchar(10) not null,
    blood         varchar(10) not null
);

create table employee
(
    emp_id        varchar(10) primary key,
    emp_name      varchar(25) not null,
    emp_address   text        not null,
    email         varchar(25) not null,
    job_role      varchar(25) not null,
    qualification varchar(25),
    salary        varchar(25) not null
);

create table salary
(
    salary_id varchar(10) primary key not null,
    emp_id    varchar(10)             not null,
    month     date                    not null,

    constraint foreign key (emp_id) references employee (emp_id) on delete cascade on update cascade
);

create table login
(
    full_name varchar(25) not null,
    user_name varchar(25) primary key,
    password  varchar(25) not null

);

CREATE TABLE doctor
(
    id          VARCHAR(10) PRIMARY KEY,
    doctor_name VARCHAR(25) NOT NULL,
    address     TEXT NOT NULL,
    email       VARCHAR(25) NOT NULL,
    number      VARCHAR(25) NOT NULL,
    type        VARCHAR(25) NOT NULL
);
create table appoinment
(
    appoinment_id varchar(10) primary key,
    patient_id    varchar(25) not null,
    age           text        not null,
    id            varchar(10) not null,
    time          varchar(10) not null,
    date          varchar(25) not null,
    fee_status    varchar(25) not null,


    constraint foreign key (patient_id) references patient (patient_id) on delete cascade on update cascade,
    constraint foreign key (id) references doctor (id) on delete cascade on update cascade
);

create table lab
(
    lab_id   varchar(10) primary key,
    emp_id   varchar(10) not null,
    l_report varchar(25) not null,

    constraint foreign key (emp_id) references employee (emp_id) on delete cascade on update cascade
);

create table payment
(
    payment_id     varchar(10) primary key,
    payment_email  varchar(25) not null,
    payment_date   date        not null,
    payment_time   time        not null,
    amount         double      not null,
    payment_method varchar(25) not null,
    patient_id     varchar(10) not null,
    appoinment_id  varchar(10) not null,

    constraint foreign key (patient_id) references patient (patient_id) on delete cascade on update cascade,
    constraint foreign key (appoinment_id) references appoinment (appoinment_id) on delete cascade on update cascade
);
create table supplier
(
    supplier_id   varchar(10) primary key,
    supplier_name varchar(25) not null,
    location      varchar(25) not null
);
create table medicine
(
    medi_code   varchar(10) primary key,
    stock       varchar(10) not null,
    supplier_id varchar(10) not null,
    location    varchar(25) not null,

    constraint foreign key (supplier_id) references supplier (supplier_id) on delete cascade on update cascade
);

create table medicalReport
(
    id           varchar(10),
    patient_id   varchar(10) not null,
    patient_name varchar(25) not null,
    date         date        not null,

    constraint foreign key (id) references doctor (id) on delete cascade on update cascade,
    constraint foreign key (patient_id) references patient (patient_id) on delete cascade on update cascade
);

create table labReport
(
    patient_id varchar(10) not null,
    l_report   varchar(25) not null,
    date       date        not null,
    time       time        not null,

    constraint foreign key (patient_id) references patient (patient_id) on delete cascade on update cascade
);