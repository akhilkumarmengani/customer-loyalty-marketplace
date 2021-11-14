create or replace TRIGGER "UPDATE_REGULAR_LOYALTY_TYPE" AFTER INSERT ON regular_loyalty_programs
FOR EACH ROW
BEGIN
   Update Brands SET loyalty_type = 'REGULAR' where brand_id = :NEW.brand_id;
END;