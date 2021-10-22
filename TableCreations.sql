
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


CREATE TABLE activities (
activity_id NUMBER PRIMARY KEY,
activity_type VARCHAR2(20) NOT NULL);

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
