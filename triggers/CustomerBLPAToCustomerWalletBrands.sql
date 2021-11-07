CREATE OR REPLACE TRIGGER CUSTOMER_BLPA_TO_CUSTOMER_WALLET_BRANDS 
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