create table sellers (
   id integer not null auto_increment,
    base_salary double precision,
    email_id varchar(255),
    full_name varchar(255),
    department_id integer,
    primary key (id)
) ENGINE=MyISAM

create table departments (
   id integer not null auto_increment,
    name varchar(255),
    primary key (id)
) ENGINE=MyISAM