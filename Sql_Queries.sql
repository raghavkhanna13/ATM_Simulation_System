create database bankmanagementsystem;
show databases;
use bankmanagementsystem;
show tables;

-- for login table
create table login (FormNo varchar(10), Card_Number varchar(30), Pin varchar(10));
select * from login;
truncate login;


-- for first table signupone table   
create table signupone (FormNo varchar(10),Name varchar(30),Father_name varchar(30),DOB varchar(20),Gender varchar(10),Email varchar(40),Marital_Status varchar(10),Address varchar(100),City varchar(20),State varchar(20),Pincode varchar(10));
select * from signupone;
truncate  signupone;


-- for table two (signuptwo is the table name)
create table signuptwo (FormNo varchar(10), Religion varchar(30),Category varchar(30),Income varchar(30),Education_Qualification varchar(30),Occupation varchar(20),PAN_Number varchar(20),Aadhar_Number varchar(20),Senior_Citizen varchar(10),Existing_Account varchar(10));   
select * from signuptwo;
truncate signuptwo;

-- for table third signupthree
create table signupthree (FormNo varchar(10), Account_Type varchar(30),Card_Number varchar(30),Pin varchar(10),Facilities varchar(150));
select * from signupthree;
truncate signupthree;   







