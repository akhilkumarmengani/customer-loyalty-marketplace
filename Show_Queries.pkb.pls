--------------------------------------------------------
--  File created - Thursday-November-11-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package Body SHOW_QUERIES_PROCEDURES
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE PACKAGE BODY "SMULKUR"."SHOW_QUERIES_PROCEDURES" AS

SQLERRM varchar2(40) := 'SQL ERROR WHILE FECTHING RECORDS';

  
--- Procedure 1
PROCEDURE  GET_RESULTS_FOR_OPTION_1_6(p_operation    IN  VARCHAR2,
                                    p_brand_username IN  VARCHAR2,
                                    x_return_message OUT VARCHAR2,
                                    x_status         OUT VARCHAR2)
                                    --customer_query_table OUT customer_table)
  IS

  counter Integer := 0;
  customer_query_record customer_record_1;
  
  CURSOR customer_cursor_1
  IS 
  
    SELECT 
            users.user_name AS username,
            customer.name AS customername
    FROM
        customers customer,
        users     users
    WHERE
        users.user_id = customer.user_id

    MINUS

    SELECT 
          users_cust.user_name AS username,
          cust.name            AS customername
    FROM
        customers_to_brands cb,
        customers           cust,
        brands              b,
        users               users_brands,
        users               users_cust
    WHERE
            users_brands.user_name = p_brand_username
        AND b.user_id = users_brands.user_id
        AND b.brand_id = cb.brand_id
        AND cb.customer_id = cust.customer_id
        AND cust.user_id = users_cust.user_id;
    
  CURSOR customer_cursor_6
  IS 
    
    SELECT 
          users_cust.user_name AS CustomerUserName,
          c.name            AS CustomerName
    FROM
        customers                                  c,
        customers_to_blp_reward_redeeming_activity cblprr,
        brands                                     b,
        Users                                      users_brand,
        Users                                      users_cust
    WHERE
             users_brand.user_name = p_brand_username
         AND b.user_id = users_brand.user_id
         AND b.brand_id = cblprr.brand_id
         AND cblprr.customer_id = c.customer_id
         AND c.user_id = users_cust.user_id
    GROUP BY
            users_cust.user_name,c.name
    HAVING
          COUNT(*) > 1;
  
 BEGIN
    
    x_return_message := 'CustomerUserId|CustomerName#';
    IF p_operation = '1' THEN
       OPEN customer_cursor_1;
       LOOP
       IF customer_cursor_1%NOTFOUND THEN
            EXIT;
        END IF;   
        counter := counter +1; 
        FETCH customer_cursor_1 INTO customer_query_record;
        x_return_message :=  concat(x_return_message,customer_query_record.customer_username);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,customer_query_record.customer_name);
        x_return_message := concat(x_return_message,'#');
        --customer_query_table(counter) := customer_query_record;
              
       END LOOP;
    END IF;
  
    IF p_operation = '6' THEN   
       OPEN customer_cursor_6;
       LOOP
       IF customer_cursor_6%NOTFOUND THEN
            EXIT;
        END IF; 
        counter := counter +1; 
        FETCH customer_cursor_6 INTO customer_query_record;
        x_return_message :=  concat(x_return_message,customer_query_record.customer_username);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,customer_query_record.customer_name);
        x_return_message := concat(x_return_message,'#');
        --customer_query_table(counter) := customer_query_record;
          
       END LOOP;
    END IF;
  
    x_status:='S';
    EXCEPTION 
        WHEN OTHERS THEN
            x_return_message:= SUBSTR(SQLERRM, 1, 200);
            x_status:='E';
  END GET_RESULTS_FOR_OPTION_1_6;
  
  
--- Procedure 2

PROCEDURE  GET_RESULTS_FOR_OPTION_2(p_operation                   IN  VARCHAR2,
                                    x_return_message              OUT VARCHAR2,
                                    x_status                      OUT VARCHAR2)
                                    --customer_loyalty_query_table  OUT customer_loyalty_table)
  IS
  counter Integer := 0;
  customer_loyalty_query_record customer_loyalty_record_1;
  
  CURSOR customer_loyalty_cursor
  IS
  
  SELECT 
        users_cust.user_name       AS customerid,
        lp.loyalty_program_id      AS loyaltyprogramid
  FROM
        customers_to_brands      cb,
        regular_loyalty_programs lp,
        users                    users_cust,
        customers                customer
  WHERE
            cb.brand_id = lp.brand_id
        AND cb.customer_id 
        NOT IN (
               SELECT
                    cblpa.customer_id
               FROM
                    customers_to_blp_activities cblpa
                )
        AND customer.customer_id = cb.customer_id
        AND users_cust.user_id = customer.user_id;
  BEGIN
    x_return_message := 'CustomerUserId|LoyaltyProgramId#';
    OPEN customer_loyalty_cursor;
    
    LOOP
        IF customer_loyalty_cursor%NOTFOUND THEN
            EXIT;
        END IF;
    
        counter := counter +1; 
        FETCH customer_loyalty_cursor INTO customer_loyalty_query_record;
        x_return_message :=  concat(x_return_message,customer_loyalty_query_record.customer_username);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,customer_loyalty_query_record.loyalty_program_id);
        x_return_message := concat(x_return_message,'#');
        --customer_loyalty_query_table(counter) := customer_loyalty_query_record;
        
        
    END LOOP;
  
    x_status:='S';
    EXCEPTION 
        WHEN OTHERS THEN
            x_return_message:= SUBSTR(SQLERRM, 1, 200);
            x_status:='E';
            
  END GET_RESULTS_FOR_OPTION_2;

--- Procedure 3

PROCEDURE  GET_RESULTS_FOR_OPTION_3(p_operation                   IN  VARCHAR2,
                                    p_brand_username              IN  VARCHAR2,
                                    x_return_message              OUT VARCHAR2,
                                    x_status                      OUT VARCHAR2)
                                   -- reward_category_query_table   OUT reward_category_table)
  IS
  counter Integer := 0;
  reward_category_query_record reward_category_record_1;
  --x_status = 'hello' ;
  CURSOR reward_category_cursor
  IS
  --select * from reward_categories where reward_category_code in(
    SELECT 
          distinct rwd.reward_category_code AS rewardcategorycode,
         rwd.reward_name       AS rewardname
    FROM
         reward_categories                  rwd,
         brands                             b,
         brands_loyalty_programs_to_rewards blpr,
         users                              users_brand
    WHERE
             rwd.reward_category_code = blpr.reward_category_code
         AND blpr.reward_value <> '-1' 
         AND blpr.brand_id = b.brand_id
         AND users_brand.user_id = b.user_id 
         AND users_brand.user_name = p_brand_username
         AND blpr.version_number = 1;
        --group by rwd.reward_category_code;--);
        --fetch next 1 rows only;
        --having rownum = min(rownum);
    
   BEGIN
     x_return_message := 'RewardCategoryCode|RewardName#';
     OPEN reward_category_cursor;
     
     LOOP
       
        counter := counter +1; 
        IF reward_category_cursor%NOTFOUND THEN
           EXIT;
        END IF;
        FETCH reward_category_cursor INTO reward_category_query_record;
        x_return_message :=  concat(x_return_message,reward_category_query_record.reward_category_code);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,reward_category_query_record.reward_name);
        x_return_message := concat(x_return_message,'#');
        --DBMS_OUTPUT.put_line(reward_category_query_record);
        --reward_category_query_table(counter) := reward_category_query_record;
        --insert into test_table(x,y,z) values(reward_category_query_table(counter).reward_category_code,reward_category_query_table(counter).reward_name,reward_category_query_table(counter).reward_name);        
        --IF reward_category_cursor%NOTFOUND THEN
        --    EXIT;
        --END IF;
        
     END LOOP;
  
    x_status:='S';
    EXCEPTION 
        WHEN OTHERS THEN
            x_return_message:= SUBSTR(SQLERRM, 1, 200);
            --x_status:='E';
    
  END GET_RESULTS_FOR_OPTION_3; 

--- Procedure 4

PROCEDURE  GET_RESULTS_FOR_OPTION_4(p_operation                  IN  VARCHAR2,
                                     p_activity_code             IN  VARCHAR2,
                                     x_return_message            OUT VARCHAR2,
                                     x_status                    OUT VARCHAR2)
                                     --brand_loyalty_query_table   OUT brand_loyalty_table)
  IS
  counter Integer := 0;
  brand_loyalty_query_record brand_loyalty_record_1;
  
  CURSOR brand_loyalty_cursor
  IS
  
    SELECT 
          b.name                  AS BrandName,
          blpa.loyalty_program_id AS LoyaltyProgramId
    FROM
          brands_loyalty_programs_to_activities blpa,
          brands                               b
    WHERE
              blpa.activity_code = p_activity_code
          AND blpa.brand_id = b.brand_id;
    
  BEGIN
    x_return_message := 'BrandName|LoyaltyProgramId#';
    OPEN brand_loyalty_cursor;
    LOOP
        counter := counter +1; 
        FETCH brand_loyalty_cursor INTO brand_loyalty_query_record;
        x_return_message :=  concat(x_return_message,brand_loyalty_query_record.brand_name);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,brand_loyalty_query_record.loyalty_program_id);
        x_return_message := concat(x_return_message,'#');
        --brand_loyalty_query_table(counter) := brand_loyalty_query_record;
        
        IF brand_loyalty_cursor%NOTFOUND THEN
            EXIT;
        END IF;
        
    END LOOP;
  
    x_status:='S';
    EXCEPTION 
        WHEN OTHERS THEN
            x_return_message:= SUBSTR(SQLERRM, 1, 200);
            x_status:='E';
            
  END GET_RESULTS_FOR_OPTION_4;
  
  
--- Procedure 5

PROCEDURE  GET_RESULTS_FOR_OPTION_5(p_operation                  IN  VARCHAR2,
                                     p_brand_username            IN  VARCHAR2,
                                     x_return_message            OUT VARCHAR2,
                                     x_status                    OUT VARCHAR2)
                                     --brand_loyalty_query_table   OUT brand_loyalty_table)
  IS
  counter Integer := 0;
  activity_instance_query_record activity_instance_record_1;
  
  CURSOR activity_cursor
  IS
  
    SELECT
            a.activity_name,
            COUNT(*) AS numberofinstances
    FROM
            brands                      b,
            customers_to_blp_activities cblpa,
            activities                  a,
            users                       brands_user
    WHERE
                brands_user.user_name = p_brand_username
            AND b.user_id = brands_user.user_id
            AND cblpa.brand_id = b.brand_id
            AND a.activity_code = cblpa.activity_code
    GROUP BY
            a.activity_name;
    
  BEGIN
    x_return_message := 'ActivityName|No_of_Instances#';
    OPEN activity_cursor;
    LOOP
        counter := counter +1; 
        FETCH activity_cursor INTO activity_instance_query_record;
        x_return_message :=  concat(x_return_message,activity_instance_query_record.activity_type);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,activity_instance_query_record.number_of_instances);
        x_return_message := concat(x_return_message,'#');
        --brand_loyalty_query_table(counter) := brand_loyalty_query_record;
        
        IF activity_cursor%NOTFOUND THEN
            EXIT;
        END IF;
        
    END LOOP;
  
    x_status:='S';
    EXCEPTION 
        WHEN OTHERS THEN
            x_return_message:= SUBSTR(SQLERRM, 1, 200);
            x_status:='E';
            
  END GET_RESULTS_FOR_OPTION_5;
  
  

--- Procedure 7

PROCEDURE  GET_RESULTS_FOR_OPTION_7(p_operation                  IN  VARCHAR2,
                                     p_threshold_val             IN  NUMBER,
                                     x_return_message            OUT VARCHAR2,
                                     x_status                    OUT VARCHAR2)
                                     --brand_query_table           OUT brand_table)
  IS
  counter Integer := 0;
  brand_query_record brand_record_1;
  
  CURSOR brand_cursor
  IS
    SELECT 
          brand_users.user_name AS BrandUserName,
          b.name AS BrandName
    FROM
          brands                                     b,
          customers_to_blp_reward_redeeming_activity cblprr,
          Users                                 brand_users
    WHERE
              
              brand_users.user_id = b.user_id
          AND b.brand_id = cblprr.brand_id
    GROUP BY
            brand_users.user_name,b.name
    HAVING
           SUM(cblprr.points_redeemed) < p_threshold_val;
  
  BEGIN
    x_return_message := 'BrandUserId|BrandName#';
    OPEN brand_cursor;
    LOOP
        IF brand_cursor%NOTFOUND THEN
            EXIT;
        END IF;
        counter := counter +1; 
        FETCH brand_cursor INTO brand_query_record;
        x_return_message :=  concat(x_return_message,brand_query_record.brand_username);
        x_return_message := concat(x_return_message,'|');
        x_return_message := concat(x_return_message,brand_query_record.brand_name);
        x_return_message := concat(x_return_message,'#');
        --brand_query_table(counter) := brand_query_record;
        
    END LOOP;
  
    x_status:='S';
    EXCEPTION 
        WHEN OTHERS THEN
            x_return_message:= SUBSTR(SQLERRM, 1, 200);
            x_status:='E';
            
  END GET_RESULTS_FOR_OPTION_7;
  

--- Procedure 8

PROCEDURE GET_RESULTS_FOR_OPTION_8(p_operation          IN  VARCHAR2,
                                   p_brand_username     IN  VARCHAR2,
                                   p_customer_name      IN  VARCHAR2,
                                   p_start_date         IN  VARCHAR2,
                                   p_end_date           IN  VARCHAR2,
                                   x_return_message     OUT VARCHAR2,
                                   x_status             OUT VARCHAR2,
                                   x_no_of_activities   OUT NUMBER)
  IS
    
  l_no_of_activites NUMBER := 0;
  
  BEGIN
  
        SELECT
               COUNT(*) INTO l_no_of_activites
        FROM
               customers_to_blp_activities cblpa,
               customers                   c,
               brands                      b,
               Users                       brand_users,
               Users                       cust_users
        WHERE
                  
                  cust_users.user_name = p_customer_name
              AND   c.user_id = cust_users.user_id
              AND cblpa.customer_id = c.customer_id
              AND brand_users.user_name = p_brand_username
              AND b.user_id = brand_users.user_id
              AND cblpa.brand_id = b.brand_id
              AND cblpa.performed_date BETWEEN to_date(p_start_date,'MM/DD/YYYY') AND to_date(p_end_date,'MM/DD/YYYY');
        x_no_of_activities := l_no_of_activites;
  
  END GET_RESULTS_FOR_OPTION_8;
 
                                   
  
END SHOW_QUERIES_PROCEDURES;

/
