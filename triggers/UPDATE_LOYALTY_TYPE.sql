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