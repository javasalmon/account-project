# account-project

Simple account RESTful API that provides a user with a CRUD service for managing simple accounts


## Swagger UI

See swagger api for documentation (http://localhost:8080/account-project/swagger-ui.html#/)

![Swagger-UI](img/swagger-api.png)
![Account-Resource-UI](img/account-resource.png)

## Deployment

Checkout the code and build it with maven using :

    clean package

To run the app :

    mvn spring-boot:run
    
To test REST API:
    
    Use postman

### Get All Accounts

![Get-Accounts1](img/getaccounts.png)

### Post Account

![Post-Account](img/postaccount.png)

### Get Accounts With New Records

![Get-Accounts2](img/getaccountwithnewrecords.png)

### Delete Account (Delete request gets 204 NO CONTENT HTTP CODE)

![Delete-Account](img/deleteaccount.png)

### Get Accounts Following Delete

![Get-Accounts3](img/getaccounts.png)


