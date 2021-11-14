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