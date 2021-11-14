describe users;

select * from users;


CREATE SEQUENCE activity_id_sequence
MINVALUE 100
START WITH 100
INCREMENT BY 1;

select activity_id_sequence.nextval from dual;

select 'ACT'||to_char(activity_id_sequence.nextval,'FM099') as ActivityId
  from dual;

CREATE SEQUENCE customer_wallet_id_seq
MINVALUE 1
START WITH 1
INCREMENT BY 1;

INSERT INTO users (USER_NAME,PASSWD,LOGIN_TYPE, NAME , ADDRESS, CONTACT_NUMBER, JOIN_DATE) values('akhilm','akhilm','CUSTOMER','Akhil','xyz',123,sysdate);

INSERT INTO users (USER_NAME,PASSWD,LOGIN_TYPE, NAME , ADDRESS, CONTACT_NUMBER, JOIN_DATE) values('akm','akm','CUSTOMER','Pron','az',69,sysdate);


--Query 4:

SELECT LOYALTY_PROGRAM_ID, Brand_ID
FROM blp_activities_to_reward_earning_rules
WHERE ACTIVITY_NAME = 'refer a friend';


--Query 3:

SELECT reward_categories.reward_name
FROM BRANDS, REWARD_CATEGORIES, BRANDS_LOYALTY_PROGRAMS_TO_REWARDS
WHERE BRANDS.BRAND_ID = brands_loyalty_programs_to_rewards.brand_id AND
brands.name = 'Brand01' and brands_loyalty_programs_to_rewards.reward_category_id = reward_categories.reward_category_id;

--Query 2:

SELECT customers_to_brands.customer_id, regular_loyalty_programs.loyalty_program_id
FROM CUSTOMERS_TO_BRANDS, REGULAR_LOYALTY_PROGRAMS
WHERE CUSTOMERS_TO_BRANDS.BRAND_ID = regular_loyalty_programs.brand_id

EXCEPT

SELECT customers_to_blp_activities.customer_id, customers_to_blp_activities.loyalty_program_id
FROM CUSTOMERS_TO_BLP_ACTIVITIES;

select * from activities;

INSERT INTO activities (Activity_Code,activity_name) VALUES ('ACT100','REFER A FRIEND');
INSERT INTO activities (Activity_Code,activity_name) VALUES ('ACT101','REFER A FRIEND');
INSERT INTO activities (Activity_Code,activity_name) VALUES ('ACT102','PURCHASE');
INSERT INTO activities (Activity_Code,activity_name) VALUES ('ACT103','LEAVE A REVIEW');

delete  from activities where Activity_Code = 'ACT100';


create table CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY
(
   CUSTOMER_ID NUMBER,
   BRAND_ID NUMBER,
   LOYALTY_PROGRAM_ID NUMBER,
   POINTS_REDEEMED NUMBER,
   REDEEMED_DATE DATE DEFAULT SYSDATE
);


DESCRIBE CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY;






/* CREATE TABLE users (
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
loyalty_program_id NUMBER,
loyalty_program_type VARCHAR2(20),
PRIMARY KEY (brand_id,loyalty_program_id) );
ALTER TABLE regular_loyalty_programs
ADD CONSTRAINT fk_regular_loyalty_programs FOREIGN KEY (brand_id) REFERENCES brands(brand_id) ON DELETE CASCADE;




CREATE TABLE tiered_loyalty_programs (
brand_id  NUMBER,
loyalty_program_id NUMBER,
points_required NUMBER,
loyalty_program_level VARCHAR2(10),
multiplier  NUMBER,
PRIMARY KEY (brand_id, loyalty_program_id)
);

ALTER TABLE tiered_loyalty_programs
ADD CONSTRAINT fk_tiered_loyalty_programs FOREIGN KEY (brand_id) REFERENCES brands(brand_id) ON DELETE CASCADE;
*/
drop table activities;
CREATE TABLE activities (
activity_code VARCHAR2(6) PRIMARY KEY,
activity_name VARCHAR2(20) );


CREATE TABLE reward_categories (
reward_category_id NUMBER PRIMARY KEY,
reward_name VARCHAR2(20) NOT NULL);

CREATE TABLE gift_cards(
reward_category_id NUMBER PRIMARY KEY,
gift_card_value NUMBER NOT NULL);

CREATE TABLE free_products(
reward_category_id NUMBER PRIMARY KEY,
free_product_name VARCHAR2(20) NOT NULL);



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





/*
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

*/
drop table tiered_loyalty_programs;
CREATE TABLE tiered_loyalty_programs (
brand_id  NUMBER,
loyalty_program_id NUMBER,
points_required NUMBER,
loyalty_program_level VARCHAR2(10),
multiplier  NUMBER,
PRIMARY KEY (brand_id, loyalty_program_id,loyalty_program_level)
);

ALTER TABLE tiered_loyalty_programs
ADD CONSTRAINT fk_tiered_loyalty_programs FOREIGN KEY (brand_id) REFERENCES brands(brand_id) ON DELETE CASCADE;

drop TABLE activities;
CREATE TABLE activities (
activity_code VARCHAR2(6),
activity_name VARCHAR2(20),
PRIMARY KEY(activity_code));


drop TABLE reward_categories;
CREATE TABLE reward_categories (
reward_category_code VARCHAR2(20),
reward_name VARCHAR2(20) NOT NULL,
PRIMARY KEY(reward_category_code));
/*
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
*/
drop table Customers_To_BLP_Activities;--done
create table Customers_To_BLP_Activities(
    customer_id number,
    brand_id  number,
    loyalty_program_id  number,
    activity_code VARCHAR2(6),
    Purchase  varchar2(20),
    Refer_A_Friend  varchar2(20),
    Leave_A_Review  varchar2(100),
    primary key (customer_id, brand_id, loyalty_program_id, activity_code),
    constraint fk_Customers_To_BLP_Activities_customers foreign key (customer_id) references customers(customer_id) on delete cascade,
    constraint fk_Customers_To_BLP_Activities_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Customers_To_BLP_Activities_loyalty_program foreign key (brand_id,loyalty_program_id) references regular_loyalty_programs(brand_id,loyalty_program_id) on delete cascade,
    constraint fk_Customers_To_BLP_Activities_activities foreign key (activity_code) references activities(activity_code) on delete cascade
);

describe ALL_CONS_COLUMNS;
drop TABLE Customers_To_BLP_Rewards;
create table Customers_To_BLP_Rewards(
    Customer_Id number,
    Brand_Id number,
    Loyalty_Program_Id number,
    Reward_Category_Code VARCHAR2(20),
    primary key (customer_id, brand_id, reward_category_code),
    constraint fk_Customers_To_BLP_Rewards_customers foreign key (customer_id) references customers(customer_id) on delete cascade,
    constraint fk_Customers_To_BLP_Rewards_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Customers_To_BLP_Rewards_loyalty_program foreign key (brand_id,Loyalty_Program_Id) references regular_loyalty_programs(brand_id,Loyalty_Program_Id) on delete cascade,
    constraint fk_Customers_To_BLP_Rewards_reward_categories foreign key (Reward_Category_Code) references reward_categories(Reward_Category_Code) on delete cascade
);

/*
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

*/
drop table Brands_Loyalty_Programs_To_Activities;--done
--not working
create table Brands_Loyalty_Programs_To_Activities(
    Brand_Id number,
    Loyalty_Program_Id  number,
    Activity_Code VARCHAR2(6),
    primary key (brand_id,activity_code),
    constraint fk_Brands_Loyalty_Programs_To_Activities_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Activities_activities foreign key (activity_code) references activities(Activity_Code) on delete cascade
);
DROP TABLE Brands_Loyalty_Programs_To_Rewards;
create table Brands_Loyalty_Programs_To_Rewards(
    Brand_Id number,
    Loyalty_Program_Id  number,
    reward_category_code VARCHAR2(20),
    primary key (brand_id,Reward_Category_Code),
    constraint fk_Brands_Loyalty_Programs_To_Rewards_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Rewards_reward_categories foreign key (Reward_Category_Code) references reward_categories(Reward_Category_Code) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Rewards_loyalty_program foreign key (brand_id,Loyalty_Program_Id) references regular_loyalty_programs(brand_id,Loyalty_Program_Id) on delete cascade
);
*/
--drop table BLP_Activities_To_Reward_Earning_Rules;
create table BLP_Activities_To_Reward_Earning_Rules(
    Brand_Id number,
    Loyalty_Program_Id number,
    Activity_Code VARCHAR2(6),
    Reward_Earning_Rule_Id number,
    primary key (brand_id, Loyalty_Program_Id,Activity_Code,Reward_Earning_Rule_Id),
    constraint fk_BLP_Activities_To_Reward_Earning_Rules_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Earning_Rules_activities foreign key (activity_code) references activities(Activity_Code) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Earning_Rules_loyalty_program foreign key (brand_id,Loyalty_Program_Id) references regular_loyalty_programs(brand_id,Loyalty_Program_Id) on delete cascade
);

DROP TABLE BLP_Activities_To_Reward_Redeeming_Rules;
create table BLP_Activities_To_Reward_Redeeming_Rules(
    Brand_Id number,
    Loyalty_Program_Id number,
    Reward_Category_Code VARCHAR2(20),
    Reward_Redeeming_Rule_Id number,
    primary key (brand_id,Reward_Category_Code,Reward_Redeeming_Rule_Id),
    constraint fk_BLP_Activities_To_Reward_Redeeming_Rules_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Redeeming_Rules_reward_categories foreign key (Reward_Category_Code) references reward_categories(Reward_Category_Code) on delete cascade,
    constraint fk_BLP_Activities_To_Reward_Redeeming_Rules_loyalty_program foreign key (Brand_Id,Loyalty_Program_Id) references regular_loyalty_programs(Brand_Id,Loyalty_Program_Id) on delete cascade
);
*/
drop table Employees_To_Activities;
create table Employees_To_Activities(
    employee_id number,
    Activity_Code VARCHAR2(6),
    primary key (employee_id, activity_code),
    constraint fk_Employees_To_Activities_activities foreign key (activity_code) references activities(Activity_Code) on delete cascade,
    constraint fk_Employees_To_Activities_employees foreign key (employee_id) references employees(employee_id) on delete cascade
);

DROP TABLE Employees_To_Reward_Categories;
create table Employees_To_Reward_Categories(
    employee_id number,
    Reward_Category_Code VARCHAR2(20),
    primary key (employee_id, Reward_Category_Code),
    constraint fk_Employees_To_Reward_Categories_reward_categories foreign key (Reward_Category_Code) references reward_categories(Reward_Category_Code) on delete cascade,
    constraint fk_Employees_To_Reward_Categories_employees foreign key (employee_id) references employees(employee_id) on delete cascade
);
*/

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
select * from reward_categories;

Update Brands SET loyalty_type = 'TIERED' where brand_id = 81;

ALTER TABLE BRANDS Modify ( COLUMN LOYALTY_TYPE NOT NULL );
ALTER TABLE BRANDS ADD CONSTRAINT LOYALTY_TYPE_Constraint DEFAULT 'REGULAR' FOR LOYALTY_TYPE;

ALTER TABLE BRANDS  
  MODIFY LOYALTY_TYPE varchar2(100) not null;  
  
drop table Brands_loyalty_programs_to_activities;

create table Brands_Loyalty_Programs_To_Activities(
    Brand_Id number,
    Loyalty_Program_Id  number,
    Activity_Code VARCHAR2(6),
    U_ID NUMBER GENERATED ALWAYS as IDENTITY(START with 1 INCREMENT by 1),
    ACTIVITY_VALUE NUMBER,
    VERSION_NUMBER NUMBER DEFAULT 1,
    primary key (brand_id,Loyalty_Program_Id,activity_code),
    constraint fk_Brands_Loyalty_Programs_To_Activities_brands foreign key (brand_id) references brands(brand_id) on delete cascade,
    constraint fk_Brands_Loyalty_Programs_To_Activities_activities foreign key (activity_code) references activities(Activity_Code) on delete cascade
);



--- Query1


Select c.customer_id, c.name
from customers c

except

select cust.customer_id as CustomerId, cust.name as CustomerName
from customers_to_brands cb, customers cust, brands b
where b.brand_id = cb.brand_id and
b.name = 'Brand02' and 
customers_to_brands.customer_id = cust.customer_id;



--- Query2

select cb.customer_id as CustomerId, lp.loyalty_program_id as LoyaltyProgramId
from customers_to_brands cb, regular_loyalty_programs lp
where cb.brand_id = lp.brand_id and  
cb.customer_id not in (select cblpa.customer_id  from customers_to_blp_activities cblpa ); 

--- Query3

SELECT
    rwd.reward_category_code AS rewardcategorycode,
    rwd.reward_name          AS rewardname
FROM
    reward_categories                  rwd,
    brands                             b,
    brands_loyalty_programs_to_rewards blpr
WHERE
        rwd.reward_category_code = blpr.reward_category_code
    AND blpr.reward_value <> '-1'
blpr.brand_id = brands.brand_id
AND b.name = 'Brand01';


--- Query 4

select b.name as BrandName, blpa.loyalty_program_id as LoyaltyProgramId
from activities a, brands_loyalty_programs_to_activites blpa, brands b
where a.name = 'refer a friend' and
a.activity_code = blpa.activity_code and
blpa.brand_id = b.brand_id;


--- Query 5


select a.activity_name, count (*) as NumberofInstances
from brands b, customers_to_blp_activities cblpa, activities a
where b.name = 'Brand01' and
cblpa.brand_id = b.brand_id and
a.activity_code = cblpa.activity_code
group by a.activity_name;

--- Query 6

SELECT
    blprr.customer_id AS customerid,
    c.name            AS customername
FROM
    customers                                  c,
    customers_to_blp_reward_redeeming_activity cblprr,
    brands                                     b
WHERE
        b.name = 'Brand01'
    AND b.brand_id = cblprr.brand_id
    AND cblprr.customer_id = c.customer_id
GROUP BY
    blprr.customer_id
HAVING
    COUNT(*) > 1;

--- Query 7

SELECT
    b.brand_id,
    b.name
FROM
    brands                                     b,
    customers_to_blp_reward_redeeming_activity cblprr
WHERE
        b.name = 'Brand01'
    AND b.brand_id = cblprr.brand_id
GROUP BY
    b.brand_id
HAVING
    SUM(cblprr.points_redeemed) > 500;

--- Query 8

SELECT
    COUNT(*) AS numberofactivities
FROM
    customers_to_blp_activities cblpa,
    customers                   c,
    brands                      b
WHERE
        c.name = 'CustomerA'
    AND b.name = 'Brand02'
    AND cblpa.customer_id = c.customer_id
    AND cblpa.brand_id = b.brand_id
    AND cblpa.performed_date BETWEEN '2021/08/01' AND '2021/9/30/';