select * 
from user_tables;


CREATE TABLE users (
user_id NUMBER PRIMARY KEY,
user_name VARCHAR2(20) UNIQUE NOT NULL,
password VARCHAR2(20) NOT NULL );

CREATE TABLE brands (
brand_id NUMBER PRIMARY KEY,
brand_name VARCHAR2(20) UNIQUE NOT NULL,
brand_address VARCHAR2(40),
join_date DATE );

CREATE TABLE customers (
customer_id NUMBER PRIMARY KEY,
customer_name Varchar2(20) NOT NULL,
customer_phone_no VARCHAR2(11),
customer_address VARCHAR2(40) ); 

CREATE TABLE employees (
employee_id NUMBER PRIMARY KEY,
employee_name VARCHAR2(20) NOT NULL ); 

CREATE TABLE regular_loyalty_programs (
brand_id NUMBER ,
loyalty_program_id NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
PRIMARY KEY (brand_id,loyalty_program_id) );

ALTER TABLE regular_loyalty_programs
ADD CONSTRAINT fk_regular_loyalty_programs FOREIGN KEY (brand_id) REFERENCES brands(brand_id) ON DELETE CASCADE;


CREATE TABLE tiered_loyalty_programs (
brand_id  NUMBER,
loyalty_program_id NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
points_required NUMBER,
loyalty_program_level VARCHAR2(10),
multiplier  NUMBER,
PRIMARY KEY (brand_id, loyalty_program_id)
);

ALTER TABLE tiered_loyalty_programs
ADD CONSTRAINT fk_tiered_loyalty_programs FOREIGN KEY (brand_id) REFERENCES brands(brand_id) ON DELETE CASCADE;


CREATE TABLE activities (
activity_id NUMBER  GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
activity_type VARCHAR2(20) NOT NULL,
PRIMARY KEY(activity_id));

CREATE TABLE reward_categories (
reward_category_id NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
reward_name VARCHAR2(20) NOT NULL,
PRIMARY KEY(reward_category_id));

CREATE TABLE gift_cards(
reward_category_id NUMBER ,
gift_card_value NUMBER NOT NULL);

CREATE TABLE free_products(
reward_category_id NUMBER PRIMARY KEY,
free_product_name VARCHAR2(20) NOT NULL);


create table employees(
    employee_id number,
    name varchar2(40),
    primary key (employee_id)
);


CREATE TABLE customers_to_brands(
    brand_id number,
    customer_id number,
    primary key (brand_id, customer_id),
    constraint fk_to_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_to_customers foreign key (customer_id) references customers(customer_id) on delete cascade
);


CREATE TABLE customer_wallet_to_customers_brands(
    customer_id  number,
    customer_wallet_id number ,
    brand_id  number,
    primary key(customer_wallet_id),
    constraint fk_customer_wallet_to_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_customer_wallet_to_customers foreign key (customer_id) references customers(customer_id) on delete cascade
);

create table Customers_To_BLP_Activities(
    customer_id number,
    brand_id  number,
    loyalty_program_id  number,
    activity_id  number,
    Purchase  varchar2(20),
    Refer_A_Friend  varchar2(20),
    Leave_A_Review  varchar2(100),
    primary key (customer_id, brand_id, loyalty_program_id, activity_id),
    constraint fk_Customers_To_BLP_Activities_customers foreign key (customer_id) references customers(customer_id) on delete cascade,
    constraint fk_Customers_To_BLP_Activities_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Customers_To_BLP_Activities_loyalty_program foreign key (brand_id,loyalty_program_id) references regular_loyalty_programs(brand_id,loyalty_program_id) on delete cascade,
    constraint fk_Customers_To_BLP_Activities_activities foreign key (activity_id) references activities(activity_id) on delete cascade
);

describe ALL_CONS_COLUMNS;

create table Customers_To_BLP_Rewards(
    Customer_Id number,
    Brand_Id number,
    Loyalty_Program_Id number,
    Reward_Category_Id number,
    primary key (customer_id, brand_id, reward_category_id),
    constraint fk_Customers_To_BLP_Rewards_customers foreign key (customer_id) references customers(customer_id) on delete cascade,
    constraint fk_Customers_To_BLP_Rewards_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Customers_To_BLP_Rewards_loyalty_program foreign key (brand_id,Loyalty_Program_Id) references regular_loyalty_programs(brand_id,Loyalty_Program_Id) on delete cascade,
    constraint fk_Customers_To_BLP_Rewards_reward_categories foreign key (Reward_Category_Id) references reward_categories(Reward_Category_Id) on delete cascade
);


create table customers_to_employees(
    customer_id number,
    employee_id number,
    primary key (customer_id, employee_id),
    constraint fk_customers_to_employees_customers foreign key (customer_id) references customers(customer_id) on delete cascade,
    constraint fk_customers_to_employees_employees foreign key (employee_id) references employees(employee_id) on delete cascade
);

-- not needed
--create table brands_to_loyalty_Programs(
--    Brand_Id number,
--    Loyalty_Program_Id number,
--    primary key (brand_id)
--);


create table Brands_Loyalty_Programs_To_Activities(
    Brand_Id number,
    Loyalty_Program_Id  number,
    Activity_Id number,
    primary key (brand_id,activity_id),
    constraint fk_Brands_Loyalty_Programs_To_Activities_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Activities_activities foreign key (activity_id) references activities(Activity_Id) on delete cascade
);

create table Brands_Loyalty_Programs_To_Rewards(
    Brand_Id number,
    Loyalty_Program_Id  number,
    Reward_Category_Id number,
    primary key (brand_id,Reward_Category_Id),
    constraint fk_Brands_Loyalty_Programs_To_Rewards_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Rewards_reward_categories foreign key (Reward_Category_Id) references reward_categories(Reward_Category_Id) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Rewards_loyalty_program foreign key (brand_id,Loyalty_Program_Id) references regular_loyalty_programs(brand_id,Loyalty_Program_Id) on delete cascade
);

create table BLP_Activities_To_Reward_Earning_Rules(
    Brand_Id number,
    Loyalty_Program_Id number,
    Activity_Id number,
    Reward_Earning_Rule_Id number,
    primary key (brand_id, Loyalty_Program_Id,Activity_Id,Reward_Earning_Rule_Id),
    constraint fk_BLP_Activities_To_Reward_Earning_Rules_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Earning_Rules_activities foreign key (activity_id) references activities(Activity_Id) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Earning_Rules_loyalty_program foreign key (brand_id,Loyalty_Program_Id) references regular_loyalty_programs(brand_id,Loyalty_Program_Id) on delete cascade
);


create table BLP_Activities_To_Reward_Redeeming_Rules(
    Brand_Id number,
    Loyalty_Program_Id number,
    Reward_Category_Id number,
    Reward_Redeeming_Rule_Id number,
    primary key (brand_id,Reward_Category_Id,Reward_Redeeming_Rule_Id),
    constraint fk_BLP_Activities_To_Reward_Redeeming_Rules_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Redeeming_Rules_reward_categories foreign key (Reward_Category_Id) references reward_categories(Reward_Category_Id) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Redeeming_Rules_loyalty_program foreign key (Brand_Id,Loyalty_Program_Id) references regular_loyalty_programs(Brand_Id,Loyalty_Program_Id) on delete cascade
);

create table Employees_To_Activities(
    employee_id number,
    Activity_Id number,
    primary key (employee_id, activity_id),
    constraint fk_Employees_To_Activities_activities foreign key (activity_id) references activities(Activity_Id) on delete cascade,
    constraint fk_Employees_To_Activities_employees foreign key (employee_id) references employees(employee_id) on delete cascade
);


create table Employees_To_Reward_Categories(
    employee_id number,
    Reward_Category_Id number,
    primary key (employee_id, Reward_Category_Id),
    constraint fk_Employees_To_Reward_Categories_reward_categories foreign key (Reward_Category_Id) references reward_categories(Reward_Category_Id) on delete cascade,
    constraint fk_Employees_To_Reward_Categories_employees foreign key (employee_id) references employees(employee_id) on delete cascade
);


/*
drop table regular_loyalty_programs;
drop table tiered_loyalty_programs;
drop table activities;
drop table reward_categories;
drop table gift_cards;
drop table free_products;


CREATE SEQUENCE brand_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE user_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE customer_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE employee_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE activity_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE reward_categories_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE loyalty_program_id_sequence
MINVALUE 1
START WITH 1
INCREMENT BY 1;

*/
