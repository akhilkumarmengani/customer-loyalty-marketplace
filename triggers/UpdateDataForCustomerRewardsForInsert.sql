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

SELECT REWARD_VALUE into rew_value_string FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

rew_value_int := TO_NUMBER(rew_value_string);

tot_points := tot_points - (n_instances*rew_value_int);

UPDATE customer_wallet_to_customers_brands SET total_points = tot_points WHERE brand_id = :NEW.brand_id and customer_id =:New.customer_id;

--combine this with line 32
SELECT NUMBER_OF_INSTANCES INTO brand_instances FROM BRANDS_LOYALTY_PROGRAMS_TO_REWARDS WHERE brand_id =:NEW.brand_id and reward_category_code = :NEW.reward_category_code and version_number = max_version_number;

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