-- This is where your DML goes
create table employee(
	id bigint not null auto_increment primary key,
	name varchar(20) not null
);

-- create the task table with id, description, due date and employee columns
