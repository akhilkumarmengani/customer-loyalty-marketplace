create or replace TRIGGER UPDATEREGULARLOYALTYTYPE 
AFTER INSERT ON regular_loyalty_programs
FOR EACH ROW
BEGIN
   Update Brands SET loyalty_type = 'REGULAR' where brand_id = :NEW.brand_id;
END;