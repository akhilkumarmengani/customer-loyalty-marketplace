--Trigger 1

create or replace TRIGGER "USER_CUSTOMER_INSERTION" AFTER INSERT ON Users
FOR EACH ROW

BEGIN

IF (:NEW.LOGIN_TYPE = 'CUSTOMER') THEN
    INSERT INTO
    customers (NAME, CONTACT_NUMBER, ADDRESS, WALLET_ID, USER_ID)
    VALUES
    (:NEW.NAME, :NEW.CONTACT_NUMBER, :NEW.ADDRESS, customer_wallet_id_seq.nextval, :NEW.USER_ID);
END IF;

IF(:NEW.LOGIN_TYPE = 'BRAND') THEN
    INSERT INTO
    brands (NAME, ADDRESS, JOIN_DATE, LOYALTY_TYPE, USER_ID)
    VALUES
    (:NEW.NAME, :NEW.ADDRESS, sysdate, null , :NEW.USER_ID);
END IF;

END;


--Trigger 2

create or replace TRIGGER "UPDATE_REGULAR_LOYALTY_TYPE" AFTER INSERT ON regular_loyalty_programs
FOR EACH ROW
BEGIN
   Update Brands SET loyalty_type = 'REGULAR' where brand_id = :NEW.brand_id;
END;


--Trigger 3

create or replace TRIGGER "UPDATE_LOYALTY_TYPE" AFTER INSERT ON Tiered_Loyalty_Programs
FOR EACH ROW
DECLARE
loyalty_val VARCHAR2(20) := 'NULL';
BEGIN
        SELECT loyalty_type into loyalty_val from Brands where brand_id = :new.Brand_Id;

        IF loyalty_val <> 'TIERED'  THEN
        --INSERT INTO regular_loyalty_programs (BRAND_ID,LOYALTY_PROGRAM_ID) VALUES (:NEW.Brand_Id,:NEW.Loyalty_Program_Id);
        Update Brands SET loyalty_type = 'TIERED' where brand_id = :NEW.brand_id;
        END IF;
END;


--Trigger 4


create or replace TRIGGER UPDATE_DATA_FOR_CUSTOMER_REWARDS_FOR_UPDATE
AFTER UPDATE ON CUSTOMERS_TO_BLP_REWARDS
FOR EACH ROW

DECLARE

tot_points Number;
rew_value_string varchar2(20);
rew_value_int varchar2(20);
n_instances Number;
customerTotalPoints Number;
loyaltytype varchar2(20);
brand_instances Number;
max_version_number Number;
rem_brand_instances NUMBER;
redeemed_points NUMBER;
TYPE customer_points_tier_record IS RECORD(
    pts_req tiered_loyalty_programs.points_required%TYPE,
    prg_level tiered_loyalty_programs.Loyalty_Program_Level%TYPE
  );

c_pts_tierd customer_points_tier_record;

CURSOR customer_tier_cursor
IS
select points_required,Loyalty_Program_Level from tiered_loyalty_programs where brand_id = :NEW.brand_id and loyalty_program_id =:New.loyalty_program_id order by points_required desc ;


BEGIN

select max(version_number) into max_version_number from BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code;


SELECT REWARD_VALUE into rew_value_string FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and version_number = max_version_number and reward_category_code = :NEW.reward_category_code;

rew_value_int := TO_NUMBER(rew_value_string);



IF :NEW.NUMBER_OF_INSTANCES > :OLD.NUMBER_OF_INSTANCES THEN

    SELECT total_points into tot_points FROM customer_wallet_to_customers_brands WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;

    n_instances := :NEW.NUMBER_OF_INSTANCES - :OLD.NUMBER_OF_INSTANCES;

    SELECT NUMBER_OF_INSTANCES,REWARD_VALUE into brand_instances,rew_value_string FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

    --SELECT REWARD_VALUE into rew_value_string FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

    rew_value_int := TO_NUMBER(rew_value_string);

    tot_points := tot_points - (n_instances*rew_value_int);

    UPDATE customer_wallet_to_customers_brands SET total_points = tot_points WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;

    IF :NEW.reward_category_code = 'REW101' then
        UPDATE customer_wallet_to_customers_brands SET number_of_gift_cards = number_of_gift_cards + n_instances
        WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;
    end if;


    --combine this with line 47
    --SELECT NUMBER_OF_INSTANCES INTO brand_instances FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

    rem_brand_instances := brand_instances - n_instances;

    UPDATE BRANDS_LOYALTY_PROGRAMS_TO_REWARDS SET NUMBER_OF_INSTANCES = rem_brand_instances WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

    SELECT loyalty_type into loyaltytype from brands where brand_id = :NEW.brand_id;

    IF loyaltytype = 'TIERED' THEN

        select total_points into customerTotalPoints from customer_wallet_to_customers_brands where brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;


        OPEN customer_tier_cursor;
        LOOP

            FETCH customer_tier_cursor INTO c_pts_tierd;

            IF c_pts_tierd.pts_req <= customerTotalPoints THEN

                UPDATE customer_wallet_to_customers_brands SET loyalty_program_level = c_pts_tierd.prg_level WHERE brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;
                EXIT;
            END IF;

            IF customer_tier_cursor%NOTFOUND THEN
                EXIT;
            END IF;

        END LOOP;

    END IF;

/*ELSE
    redeemed_points := rew_value_int *(:OLD.NUMBER_OF_INSTANCES-:NEW.NUMBER_OF_INSTANCES);
    INSERT INTO CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY (customer_id,brand_id,loyalty_program_id,points_redeemed,redeemed_date) VALUES (:NEW.customer_id,:NEW.brand_id,:NEW.loyalty_program_id,redeemed_points,SYSDATE);
*/
END IF;

END;


--Trigger 5

create or replace TRIGGER "UPDATE_DATA_FOR_CUSTOMER_REWARDS_FOR_INSERT" AFTER INSERT ON CUSTOMERS_TO_BLP_REWARDS
FOR EACH ROW
DECLARE
tot_points Number;
rew_value_string varchar2(20);
rew_value_int varchar2(20);
n_instances Number;
customerTotalPoints Number;
loyaltytype varchar2(20);
brand_instances Number;
max_version_number Number;
rem_brand_instances NUMBER;
TYPE customer_points_tier_record IS RECORD(
    pts_req tiered_loyalty_programs.points_required%TYPE,
    prg_level tiered_loyalty_programs.Loyalty_Program_Level%TYPE
  );

c_pts_tierd customer_points_tier_record;

CURSOR customer_tier_cursor
IS
select points_required,Loyalty_Program_Level from tiered_loyalty_programs where brand_id = :NEW.brand_id and loyalty_program_id =:New.loyalty_program_id order by points_required desc ;

BEGIN

SELECT total_points into tot_points FROM customer_wallet_to_customers_brands WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;

n_instances := :NEW.NUMBER_OF_INSTANCES;

select max(version_number) into max_version_number from BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code;

SELECT REWARD_VALUE,NUMBER_OF_INSTANCES into rew_value_string,brand_instances FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;
--SELECT REWARD_VALUE into rew_value_string FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

rew_value_int := TO_NUMBER(rew_value_string);

tot_points := tot_points - (n_instances*rew_value_int);

UPDATE customer_wallet_to_customers_brands SET total_points = tot_points WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;


IF :NEW.reward_category_code = 'REW101' then
    UPDATE customer_wallet_to_customers_brands SET number_of_gift_cards = number_of_gift_cards + n_instances
    WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;
end if;


--combine this with line 32
--SELECT NUMBER_OF_INSTANCES INTO brand_instances FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

rem_brand_instances := brand_instances - n_instances;

UPDATE BRANDS_LOYALTY_PROGRAMS_TO_REWARDS SET NUMBER_OF_INSTANCES = rem_brand_instances WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

SELECT loyalty_type into loyaltytype from brands where brand_id = :NEW.brand_id;

IF loyaltytype = 'TIERED' THEN

    select total_points into customerTotalPoints from customer_wallet_to_customers_brands where brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;


    OPEN customer_tier_cursor;
    LOOP

        FETCH customer_tier_cursor INTO c_pts_tierd;

        IF c_pts_tierd.pts_req <= customerTotalPoints THEN

            UPDATE customer_wallet_to_customers_brands SET loyalty_program_level = c_pts_tierd.prg_level WHERE brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;
            EXIT;
        END IF;

        IF customer_tier_cursor%NOTFOUND THEN
            EXIT;
        END IF;

  END LOOP;

END IF;
END;


--Trigger 6

create or replace TRIGGER "CUSTOMER_TO_BRANDS" AFTER INSERT ON Customers_To_Brands
FOR EACH ROW
DECLARE
custWalletId NUMBER;
brandId Number;
loyaltyId Number;
loyaltyType varchar2(20);
loyaltyTypeCheck varchar2(20) := NULL;
BEGIN
        select loyalty_type into loyaltyType  from brands where brand_id = :NEW.brand_Id;

        select loyalty_program_id into loyaltyId from regular_loyalty_programs where brand_id = :NEW.brand_Id;

        select wallet_id into custWalletId from customers where customer_id = :NEW.customer_id;

        IF loyaltyType = 'REGULAR' THEN
            loyaltyTypeCheck := 'REGULAR';
        END IF;

        IF loyaltyType ='TIERED' THEN
            select loyalty_program_level into loyaltyTypeCheck from tiered_loyalty_programs where Loyalty_Program_id = loyaltyId and multiplier =1;
        END IF;

        INSERT INTO customer_wallet_to_customers_brands (Customer_Id, Brand_id, Loyalty_Program_id, wallet_id, Total_Points,Loyalty_Program_Level,number_of_gift_cards)
        VALUES (:NEW.customer_id,:NEW.brand_Id,loyaltyId,custWalletId,0,loyaltyTypeCheck,0);

END;


--Trigger 7

create or replace TRIGGER CUSTOMER_BLPA_TO_CUSTOMER_WALLET_BRANDS
AFTER INSERT ON CUSTOMERS_TO_BLP_ACTIVITIES
FOR EACH ROW
DECLARE
loyaltyType varchar2(20);
customerTotalPoints Number;

TYPE customer_points_tier_record IS RECORD(
    pts_req tiered_loyalty_programs.points_required%TYPE,
    prg_level tiered_loyalty_programs.Loyalty_Program_Level%TYPE
  );

c_pts_tierd customer_points_tier_record;

CURSOR customer_tier_cursor
IS
select points_required,Loyalty_Program_Level from tiered_loyalty_programs where brand_id = :NEW.brand_id and loyalty_program_id =:New.loyalty_program_id order by points_required desc ;

BEGIN

UPDATE customer_wallet_to_customers_brands SET total_points = total_points+:NEW.Points_Earned WHERE brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;

SELECT loyalty_type into loyaltytype from brands where brand_id = :NEW.brand_id;

IF loyaltytype = 'TIERED' THEN

select total_points into customerTotalPoints from customer_wallet_to_customers_brands where brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;


OPEN customer_tier_cursor;
 LOOP

        FETCH customer_tier_cursor INTO c_pts_tierd;
        IF c_pts_tierd.pts_req <= customerTotalPoints THEN

        UPDATE customer_wallet_to_customers_brands SET loyalty_program_level = c_pts_tierd.prg_level WHERE brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;
        EXIT;
        END IF;
        IF customer_tier_cursor%NOTFOUND THEN
            EXIT;
        END IF;

  END LOOP;

END IF;

END;


--Trigger 8

create or replace TRIGGER CUSTOMER_BLPA_CUSTOMER_WALLET_BRANDS_ON_UPDATE
AFTER UPDATE ON CUSTOMERS_TO_BLP_ACTIVITIES
FOR EACH ROW
DECLARE
loyaltyType varchar2(20);
customerTotalPoints Number;

TYPE customer_points_tier_record IS RECORD(
    pts_req tiered_loyalty_programs.points_required%TYPE,
    prg_level tiered_loyalty_programs.Loyalty_Program_Level%TYPE
  );

c_pts_tierd customer_points_tier_record;

CURSOR customer_tier_cursor
IS
select points_required,Loyalty_Program_Level from tiered_loyalty_programs where brand_id = :NEW.brand_id and loyalty_program_id =:New.loyalty_program_id order by points_required desc ;

BEGIN

UPDATE customer_wallet_to_customers_brands SET total_points = total_points + :NEW.Points_Earned - :OLD.points_earned WHERE brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;

SELECT loyalty_type into loyaltytype from brands where brand_id = :NEW.brand_id;

IF loyaltytype = 'TIERED' THEN

select total_points into customerTotalPoints from customer_wallet_to_customers_brands where brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;


OPEN customer_tier_cursor;
 LOOP

        FETCH customer_tier_cursor INTO c_pts_tierd;
        IF c_pts_tierd.pts_req <= customerTotalPoints THEN

        UPDATE customer_wallet_to_customers_brands SET loyalty_program_level = c_pts_tierd.prg_level WHERE brand_id = :NEW.brand_id and customer_id = :NEW.customer_id;
        EXIT;
        END IF;
        IF customer_tier_cursor%NOTFOUND THEN
            EXIT;
        END IF;

  END LOOP;

END IF;

END;