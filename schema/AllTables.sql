    CREATE TABLE ACTIVITIES
   (	ACTIVITY_CODE VARCHAR2(6 BYTE) NOT NULL,
	    ACTIVITY_NAME VARCHAR2(20 BYTE) NOT NULL,
        PRIMARY KEY (ACTIVITY_CODE)
   );

   CREATE TABLE USERS
   (	USER_ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	    USER_NAME VARCHAR2(60 BYTE),
	    PASSWD VARCHAR2(60 BYTE),
	    LOGIN_TYPE VARCHAR2(60 BYTE),
	    NAME VARCHAR2(60 BYTE),
	    ADDRESS VARCHAR2(100 BYTE),
	    JOIN_DATE DATE DEFAULT SYSDATE,
	    CONTACT_NUMBER VARCHAR2(60 BYTE),
        PRIMARY KEY (USER_ID)
   );

  ALTER TABLE USERS MODIFY (USER_NAME NOT NULL ENABLE);
  ALTER TABLE USERS MODIFY (PASSWD NOT NULL ENABLE);
  ALTER TABLE USERS ADD UNIQUE (USER_NAME);


   CREATE TABLE BRANDS
   (	BRAND_ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	    NAME VARCHAR2(60 BYTE),
	    ADDRESS VARCHAR2(60 BYTE),
	    JOIN_DATE DATE DEFAULT SYSDATE,
	    LOYALTY_TYPE VARCHAR2(100 BYTE),
	    USER_ID NUMBER,
        PRIMARY KEY (BRAND_ID)
   );

    ALTER TABLE BRANDS ADD CONSTRAINT FK_BRANDS_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ENABLE;

    CREATE TABLE CUSTOMERS
   (	CUSTOMER_ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	    NAME VARCHAR2(60 BYTE),
	    CONTACT_NUMBER VARCHAR2(60 BYTE),
	    ADDRESS VARCHAR2(60 BYTE),
	    WALLET_ID NUMBER,
	    USER_ID NUMBER,
        PRIMARY KEY (CUSTOMER_ID)
   );

   ALTER TABLE CUSTOMERS ADD CONSTRAINT FK_CUSTOMERS_USERS FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE ENABLE;


    CREATE TABLE REWARD_CATEGORIES
   (	REWARD_CATEGORY_CODE VARCHAR2(20 BYTE) NOT NULL,
	    REWARD_NAME VARCHAR2(20 BYTE),
        PRIMARY KEY (REWARD_CATEGORY_CODE)
   );

   ALTER TABLE REWARD_CATEGORIES MODIFY (REWARD_NAME NOT NULL ENABLE);

    CREATE TABLE REGULAR_LOYALTY_PROGRAMS
   (	BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	    LP_CODE VARCHAR2(100 BYTE),
	    LP_NAME VARCHAR2(100 BYTE),
        PRIMARY KEY (BRAND_ID, LOYALTY_PROGRAM_ID)
   );

    ALTER TABLE REGULAR_LOYALTY_PROGRAMS ADD CONSTRAINT FK_REGULAR_LOYALTY_PROGRAMS FOREIGN KEY (BRAND_ID) REFERENCES BRANDS (BRAND_ID) ON DELETE CASCADE ENABLE;


    CREATE TABLE TIERED_LOYALTY_PROGRAMS
   (	BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    POINTS_REQUIRED NUMBER,
	    LOYALTY_PROGRAM_LEVEL VARCHAR2(10 BYTE),
	    MULTIPLIER NUMBER,
        PRIMARY KEY (BRAND_ID, LOYALTY_PROGRAM_ID, LOYALTY_PROGRAM_LEVEL)
   );

   ALTER TABLE TIERED_LOYALTY_PROGRAMS ADD CONSTRAINT FK_TIERED_LOYALTY_PROGRAMS_LOYALTY_PROGRAM_ID FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;


   CREATE TABLE BRANDS_LOYALTY_PROGRAMS_TO_REWARDS
   (	U_ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	    BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    REWARD_CATEGORY_CODE VARCHAR2(20 BYTE),
	    REWARD_VALUE VARCHAR2(40 BYTE),
	    NUMBER_OF_INSTANCES NUMBER,
	    VERSION_NUMBER NUMBER DEFAULT 1,
	    EXPIRY_DATE DATE,
        PRIMARY KEY (U_ID)
   );

    ALTER TABLE BRANDS_LOYALTY_PROGRAMS_TO_REWARDS ADD CONSTRAINT FK_BRANDS_LOYALTY_PROGRAMS_TO_REWARDS_ID FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;
    ALTER TABLE BRANDS_LOYALTY_PROGRAMS_TO_REWARDS ADD CONSTRAINT FK_BRANDS_LOYALTY_PROGRAMS_TO_REWARDS_REWARD_CATEGORIES FOREIGN KEY (REWARD_CATEGORY_CODE) REFERENCES REWARD_CATEGORIES (REWARD_CATEGORY_CODE) ON DELETE CASCADE ENABLE;


    CREATE TABLE BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES
   (	BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    ACTIVITY_CODE VARCHAR2(6 BYTE),
	    U_ID NUMBER GENERATED ALWAYS AS IDENTITY MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE ,
	    ACTIVITY_VALUE NUMBER,
	    VERSION_NUMBER NUMBER DEFAULT 1,
        PRIMARY KEY (U_ID)
   );

    ALTER TABLE BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES ADD CONSTRAINT FK_BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES_BRANDS_LOYALTY_PROGRAM FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;
    ALTER TABLE BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES ADD CONSTRAINT FK_BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES_ACTIVITIES FOREIGN KEY (ACTIVITY_CODE) REFERENCES ACTIVITIES (ACTIVITY_CODE) ON DELETE CASCADE ENABLE;



    CREATE TABLE CUSTOMERS_TO_BRANDS
   (	BRAND_ID NUMBER,
	    CUSTOMER_ID NUMBER,
        PRIMARY KEY (BRAND_ID, CUSTOMER_ID)
   );

   ALTER TABLE CUSTOMERS_TO_BRANDS ADD CONSTRAINT FK_CUSTOMERS_TO_BRANDS_BRANDS FOREIGN KEY (BRAND_ID) REFERENCES BRANDS (BRAND_ID) ON DELETE CASCADE ENABLE;
   ALTER TABLE CUSTOMERS_TO_BRANDS ADD CONSTRAINT FK_CUSTOMERS_TO_BRANDS_CUSTOMERS FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (CUSTOMER_ID) ON DELETE CASCADE ENABLE;


    CREATE TABLE CUSTOMERS_TO_BLP_REWARDS
   (	CUSTOMER_ID NUMBER,
	    BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    REWARD_CATEGORY_CODE VARCHAR2(20 BYTE),
	    NUMBER_OF_INSTANCES NUMBER,
	    U_ID NUMBER,
	    PERFORMED_DATE DATE,
	    POINTS_REDEEMED NUMBER,
        PRIMARY KEY (CUSTOMER_ID, BRAND_ID, REWARD_CATEGORY_CODE, PERFORMED_DATE)
   );

   ALTER TABLE CUSTOMERS_TO_BLP_REWARDS ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_REWARDS_BRANDS_ID FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;
   ALTER TABLE CUSTOMERS_TO_BLP_REWARDS ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_REWARDS_CUSTOMERS FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (CUSTOMER_ID) ON DELETE CASCADE ENABLE;
   ALTER TABLE CUSTOMERS_TO_BLP_REWARDS ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_REWARDS_U_ID FOREIGN KEY (U_ID) REFERENCES BRANDS_LOYALTY_PROGRAMS_TO_REWARDS (U_ID) ON DELETE CASCADE ENABLE;


    CREATE TABLE CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY
   (	CUSTOMER_ID NUMBER,
	    BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    POINTS_REDEEMED NUMBER,
	    REDEEMED_DATE DATE DEFAULT SYSDATE,
        PRIMARY KEY (CUSTOMER_ID, BRAND_ID)
   );

   ALTER TABLE CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY_BRANDS_ID FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;
   ALTER TABLE CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_REWARD_REDEEMING_ACTIVITY_CUSTOMERS FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (CUSTOMER_ID) ON DELETE CASCADE ENABLE;


    CREATE TABLE CUSTOMERS_TO_BLP_ACTIVITIES
   (	CUSTOMER_ID NUMBER,
	    BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    WALLET_ID NUMBER,
	    ACTIVITY_CODE VARCHAR2(6 BYTE),
	    POINTS_EARNED NUMBER,
	    PERFORMED_DATE DATE DEFAULT SYSDATE,
	    U_ID NUMBER,
	    NUMBER_OF_INSTANCES NUMBER,
        PRIMARY KEY (CUSTOMER_ID, BRAND_ID, ACTIVITY_CODE, PERFORMED_DATE)
   );

    ALTER TABLE CUSTOMERS_TO_BLP_ACTIVITIES ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_ACTIVITIES_BRANDS_ID FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;
    ALTER TABLE CUSTOMERS_TO_BLP_ACTIVITIES ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_ACTIVITIES_CUSTOMERS FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (CUSTOMER_ID) ON DELETE CASCADE ENABLE;
    ALTER TABLE CUSTOMERS_TO_BLP_ACTIVITIES ADD CONSTRAINT FK_CUSTOMERS_TO_BLP_ACTIVITIES_U_ID FOREIGN KEY (U_ID) REFERENCES BRANDS_LOYALTY_PROGRAMS_TO_ACTIVITIES (U_ID) ON DELETE CASCADE ENABLE;


    CREATE TABLE CUSTOMER_WALLET_TO_CUSTOMERS_BRANDS
   (	CUSTOMER_ID NUMBER,
	    BRAND_ID NUMBER,
	    LOYALTY_PROGRAM_ID NUMBER,
	    WALLET_ID NUMBER,
	    TOTAL_POINTS NUMBER,
	    LOYALTY_PROGRAM_LEVEL VARCHAR2(10 BYTE),
	    NUMBER_OF_GIFT_CARDS NUMBER,
        PRIMARY KEY (CUSTOMER_ID, BRAND_ID)
   );

    ALTER TABLE CUSTOMER_WALLET_TO_CUSTOMERS_BRANDS ADD CONSTRAINT FK_CUSTOMER_WALLET_TO_CUSTOMERS_BRANDS_ID FOREIGN KEY (BRAND_ID, LOYALTY_PROGRAM_ID) REFERENCES REGULAR_LOYALTY_PROGRAMS (BRAND_ID, LOYALTY_PROGRAM_ID) ON DELETE CASCADE ENABLE;
    ALTER TABLE CUSTOMER_WALLET_TO_CUSTOMERS_BRANDS ADD CONSTRAINT FK_BRANDS_LOYALTY_PROGRAMS_TO_REWARDS_CUSTOMERS FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMERS (CUSTOMER_ID) ON DELETE CASCADE ENABLE;

    CREATE SEQUENCE  CUSTOMER_WALLET_ID_SEQ  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;