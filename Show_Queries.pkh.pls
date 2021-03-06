--------------------------------------------------------
--  File created - Thursday-November-11-2021   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Package SHOW_QUERIES_PROCEDURES
--------------------------------------------------------

  CREATE OR REPLACE EDITIONABLE PACKAGE "SMULKUR"."SHOW_QUERIES_PROCEDURES" AS 

  -- TODO enter package declarations (types, exceptions, methods etc) here */ 
  TYPE customer_record_1 IS RECORD(
    customer_username users.user_name%TYPE,
    customer_name customers.name%TYPE
  );
  
  TYPE customer_loyalty_record_1 IS RECORD(
    customer_username users.user_name%TYPE,
    loyalty_program_id regular_loyalty_programs.loyalty_program_id%TYPE
  );
  
  TYPE reward_category_record_1 IS RECORD(
    reward_category_code reward_categories.reward_category_code%TYPE,
    reward_name reward_categories.reward_name%TYPE
  );

  TYPE brand_loyalty_record_1 IS RECORD(
    brand_name brands.name%TYPE,
    loyalty_program_id regular_loyalty_programs.loyalty_program_id%TYPE
  );
    
   TYPE activity_instance_record_1 IS RECORD(
    activity_type activities.activity_name%TYPE,
    number_of_instances NUMBER
  );
    
  TYPE brand_record_1 IS RECORD(
    brand_username users.user_name%TYPE,
    brand_name brands.name%TYPE
  );
  
  TYPE customer_table_1 IS TABLE OF customer_record_1  INDEX BY binary_integer;
  
  TYPE customer_loyalty_table_1 IS TABLE OF customer_loyalty_record_1 INDEX BY binary_integer;
  
  TYPE reward_category_table_1 IS TABLE OF reward_category_record_1 INDEX BY binary_integer;

  TYPE brand_loyalty_table_1 IS TABLE OF brand_loyalty_record_1 INDEX BY binary_integer;
  
  TYPE activity_instance_table_1 IS TABLE OF activity_instance_record_1 INDEX BY binary_integer;

  TYPE brand_table_1 IS TABLE OF brand_record_1 INDEX BY binary_integer; 
  
  PROCEDURE GET_RESULTS_FOR_OPTION_1_6
   (
   p_operation          IN  VARCHAR2,
   p_brand_username     IN  VARCHAR2,
   x_return_message     OUT VARCHAR2,
   x_status             OUT VARCHAR2);
   --customer_query_table OUT customer_table);
  
  PROCEDURE  GET_RESULTS_FOR_OPTION_2
   (
   p_operation                   IN  VARCHAR2,
   x_return_message              OUT VARCHAR2,
   x_status                      OUT VARCHAR2);
   --customer_loyalty_query_table  OUT customer_loyalty_table);
   
  PROCEDURE GET_RESULTS_FOR_OPTION_3
   (
   p_operation                 IN  VARCHAR2,
   p_brand_username            IN  VARCHAR2,
   x_return_message            OUT VARCHAR2,
   x_status                    OUT VARCHAR2);
   --reward_category_query_table OUT reward_category_table);

  PROCEDURE GET_RESULTS_FOR_OPTION_4
   (
   p_operation                 IN  VARCHAR2,
   p_activity_code             IN  VARCHAR2,
   x_return_message            OUT VARCHAR2,
   x_status                    OUT VARCHAR2);
   --brand_loyalty_query_table   OUT brand_loyalty_table);
   
   PROCEDURE GET_RESULTS_FOR_OPTION_5
   (
   p_operation                 IN  VARCHAR2,
   p_brand_username            IN  VARCHAR2,
   x_return_message            OUT VARCHAR2,
   x_status                    OUT VARCHAR2);
   --brand_loyalty_query_table   OUT brand_loyalty_table);
   
      
   PROCEDURE GET_RESULTS_FOR_OPTION_7
   (
   p_operation          IN  VARCHAR2,
   p_threshold_val      IN  NUMBER,
   x_return_message     OUT VARCHAR2,
   x_status             OUT VARCHAR2);
   --brand_query_table    OUT brand_table);
   
   PROCEDURE GET_RESULTS_FOR_OPTION_8
   (
   p_operation          IN  VARCHAR2,
   p_brand_username     IN  VARCHAR2,
   p_customer_name      IN  VARCHAR2,
   p_start_date         IN  VARCHAR2,
   p_end_date           IN  VARCHAR2,
   x_return_message     OUT VARCHAR2,
   x_status             OUT VARCHAR2,
   x_no_of_activities   OUT NUMBER);
   
 
END SHOW_QUERIES_PROCEDURES;

/
