# Customer Loyalty Program

1. **ER Diagram**
   ```customer-loyatly-program/CustomerLoyaltyMarketPlaceERDiagram.pdf```
2. **SQL files**
   1. **Tables, Index and Insert files:**
      1. Table Creation : ```customer-loyalty-program/schema/AllTables.sql```
      2. Indices : ```customer-loyalty-program/schema/Indexes.sql```
      3. Insert Statement : ```customer-loyalty-program/schema/InsertStatements.sql```
   2. **Triggers**
      1. All Triggers Used : ```customer-loyalty-program/triggers/AllTriggersCombined.sql```
      2. Individual Trigger Files : ```customer-loyalty-program/triggers/``` contains individual files each trigger.
3. **(a) Constraints** <br>
   1. ```customer-loyalty-program/schema/AllTables.sql``` contains all the constraints in the database design. 

   **(b) Functional Dependencies** <br>
   1. ```customer-loyatly-program/FunctionalDependencies.pdf```

4. **Executable JAR file**

## **Installation Steps**

Unzip the JAR provided in step 4( [jar]() ). Use IntelliJ/Eclipse IDE or any other framework which can run compile and run Java files.
Steps to run the application:
1. Open the folder in the IDE
2. Navigate to the ```customer-loyalty-program/src/main/```
3. Right Click on the file and run as Java Application.
4. User will see the options in the terminal


**Note:** 
1. Anytime a user is asked for input from the application, please enter **Integer** options only. <br>
2. See the options available in that page and then select the appropriate option** <br>

### Types Of Users:
There are three types of Users: <br>
1. Admin
2. Brand
3. Customers

Any user who wants to use application has to Sign Up and then Log In. <br>
1. Login <br>
2. Sign Up <br>
3. showQueries <br>
4. Exit <br>

## Admin 

### Admin Related Pages
Admin can perform operations in the following pages:
1. Add brand
2. Add customer
3. Show brand’s info
4. Show customer’s info 5. Add activity type
6. Add reward type
7. Log out



## Brand

1. Create an account using Sign Up option. <br>
2. Login using the user name and password provided while signing up. <br>
3. Select the desired option from the available options. <br>

### Brand Related Pages

Brand can perform operations in the following pages:
1. Add Loyalty Program 
2. Add RE Rules
3. Update RE Rules
4. Add RR Rules
5. Update RR Rules
6. Validate Loyalty Program 
7. Log out

### Customer 

1. Create an account using Sign Up option. <br>
2. Login using the user name and password provided while signing up. <br>
3. Select the desired option from the available options. <br>

### Cutsomer Related Pages
Customer can perform operations in the following pages:
1. Customer Landing
2. Enroll in Loyalty Program
3. Reward Activities
4. Purchase
5. Leave a review
6. Refer a friend
7. View Wallet
8. Redeem Points







