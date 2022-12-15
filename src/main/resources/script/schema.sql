CREATE TABLE `Employee`
(
    `id`   VARCHAR(20) NOT NULL primary key ,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `Department`
(
    `id`   VARCHAR(10) NOT NULL primary key ,
    `name` VARCHAR(50) NOT NULL
);

CREATE TABLE `Department_info`
(
    `department_id` VARCHAR(10) NOT NULL,
    `employee_id`   VARCHAR(20) NOT NULL,
    constraint
    primary key (department_id,employee_id),
    foreign key (department_id) references Department(id),
    foreign key (employee_id) references Employee(id)
);

-- https://www.erdcloud.com/d/tSPEtesaaMDKZMMH5
