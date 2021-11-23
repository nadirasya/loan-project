# Loan Project 

Loan project is a mini application for loan and built by microservices. 
In this project there are 3 services: 
- account-service
- loan-initiation-service
- payment-service

This application is currently developed using: 
- Spring 
- Kotlin
- Sqitch
- Postgres
- AWS (soon to be deployed)

# Account Service 
This service is used to create user account and get it's data 

# Loan Initiation Service 
This service is used to add a new loan and also check whether user is allowed to create a new loan. And also get loans data 

# Payment Service
This service is used to paid loan and also calculating the amount of pinalty when user paid the loan later then the due date. 
The base amount of pinalty is currenty set to be static but there is still a room for improvement where the amount set to
be dynamic. 
